package com.example.myshop.utils

sealed class Resource<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {

    class Success<T>(data: T?=null) : Resource<T>(data=data)
    class Loading<T> : Resource<T>()
    class Error<T>(errorMessage: String) : Resource<T>(errorMessage=errorMessage)
}