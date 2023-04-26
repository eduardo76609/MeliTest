package com.test.melitest.core.network

sealed class RequestResult<out T> {
    data class Success<out T>(val data: T?) : RequestResult<T>()
    data class Failure(val errorData: ErrorResponse) : RequestResult<Nothing>()
}