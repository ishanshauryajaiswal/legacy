package com.example.playcompose.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StocksApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListing(
        @Query("apikey") apiKey: String
    ): ResponseBody

    companion object{
        const val BASE_URL = "https://alphavantage.co"
        const val API_KEY = "M9M5BZNOPJYGXDHX"
    }
}