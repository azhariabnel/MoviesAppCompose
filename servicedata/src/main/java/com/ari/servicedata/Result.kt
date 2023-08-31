package com.ari.servicedata

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}

data class ResultList<T>(
    val data : List<T>,
    val success : Boolean,
    val message : String,
    val statusCode : Int
)

data class ResultData<T>(
    val data : T,
    val success : Boolean,
    val message : String,
    val statusCode : Int
)