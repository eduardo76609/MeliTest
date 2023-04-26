package com.test.melitest.core.network

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message") val message: String,
    @SerializedName("error") val error: String,
    @SerializedName("status") val code: Int
){
    override fun toString(): String {
        return "$code - $error - $message"
    }
}