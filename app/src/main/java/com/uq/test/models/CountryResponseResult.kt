package com.uq.test.models

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class CountryResponseResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : CountryResponseResult<T>()
    data class ErrorString<out T : Any>(val error: String) : CountryResponseResult<T>()
    data class ErrorException<out T : Any>(val exception: Exception) : CountryResponseResult<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is ErrorString -> "Error[string=$error]"
            is ErrorException -> "Error[exception=$exception]"
        }
    }
}