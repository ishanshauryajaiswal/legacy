package com.example.phonepe.model

import java.lang.Error


data class Resource<ResultType>(var status: Status, var data: ResultType? = null, var error: Error? = null) {

    companion object {
        /**
         * Creates [Resource] with [Status.SUCCESS] and [data]
         */
        fun <ResultType> success(data: ResultType): Resource<ResultType> = Resource(Status.SUCCESS, data)

        /**
         * Creates [Resource] with [Status.LOADING] to notify UI to load
         */
        fun <ResultType> loading(): Resource<ResultType> = Resource(Status.LOADING)

        /**
         * Creates [Resource] with [Status.ERROR] and passes the error object
         */
        fun <ResultType> error(error: Error?): Resource<ResultType> = Resource(Status.ERROR, error = error)

    }
}