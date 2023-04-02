package com.raza.acronymsmeaning.utils

import retrofit2.Response

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error<T>(val response: Response<T>) : NetworkResult<T>()
}