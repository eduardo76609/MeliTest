package com.test.melitest.core.network

import com.google.gson.Gson
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

private const val UNKNOWN_CODE = 3
private const val UNKNOWN_ERROR = "Unknown error"

private const val TIMEOUT_CODE = 1
private const val TIMEOUT_ERROR = "Time Out"
private const val TIMEOUT_MESSAGE = "Time is up, please try again"

private const val INTERNET_ACCESS_CODE = 2
private const val INTERNET_ACCESS_ERROR = "Internet"
private const val INTERNET_ACCESS_MESSAGE =
    "We don't have internet access, please check your internet and check again"

abstract class ManageRequest {

    suspend fun <T> safeApiCall(call: suspend () -> Response<T>): RequestResult<T> {
        return try {
            val apiResponse = call.invoke()
            if (apiResponse.isSuccessful && apiResponse.code() == 200) {
                RequestResult.Success(apiResponse.body())
            } else {
                findMessageFromApiErrorCodes(apiResponse)
            }
        } catch (throwable: Throwable) {
            findExceptionType(throwable)
        }
    }

    private fun <T> findMessageFromApiErrorCodes(response: Response<T>): RequestResult<T> {
        response.let {
            val errorResponse: ErrorResponse = Gson().fromJson(
                response.errorBody()?.charStream()?.readText(),
                ErrorResponse::class.java
            )
            return RequestResult.Failure(
                errorResponse
            )
        }
    }

    private fun <T> findExceptionType(throwable: Throwable): RequestResult<T> {
        throwable.let {
            val error: ErrorResponse = when (throwable) {
                is SocketTimeoutException -> ErrorResponse(
                    code = TIMEOUT_CODE,
                    error = TIMEOUT_ERROR,
                    message = TIMEOUT_MESSAGE
                )

                is UnknownHostException -> ErrorResponse(
                    code = INTERNET_ACCESS_CODE,
                    message = INTERNET_ACCESS_MESSAGE,
                    error = INTERNET_ACCESS_ERROR
                )

                else -> ErrorResponse(
                    code = UNKNOWN_CODE,
                    error = UNKNOWN_ERROR,
                    message = throwable.cause.toString()
                )
            }
            return RequestResult.Failure(error)
        }
    }

}