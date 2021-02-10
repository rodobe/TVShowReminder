package com.tooldev.wabiPlay.data.api

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this){
            is Success<*> -> "Succes[data=$data]"
            is Error -> "Error[exception$exception]"
        }
    }

}

val Result<*>.succeeded
    get() = this is Result.Success && data != null