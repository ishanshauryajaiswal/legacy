package com.example.falcone.model

data class Resource<T, K : GenericErrorResponse> constructor(val status: ResponseStatus, val data: T? = null, val errorResponse : K? = null) {

    companion object {
        fun <T, K: GenericErrorResponse> success(data: T? = null): Resource<T, K> {
            return Resource(ResponseStatus.SUCCESS, data = data)
        }

        fun <T, K: GenericErrorResponse>  error(responseData: K? = null): Resource<T, K> {
            return Resource(ResponseStatus.ERROR, errorResponse = responseData)
        }

        fun <T, K: GenericErrorResponse>  loading(data: T? = null): Resource<T, K> {
            return Resource(ResponseStatus.LOADING, data)
        }
    }
}

open class GenericErrorResponse(val throwable: Throwable?)

enum class ResponseStatus {
    SUCCESS, ERROR, LOADING
}