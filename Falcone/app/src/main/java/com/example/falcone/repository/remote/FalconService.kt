package com.example.falcone.repository.remote

import com.example.falcone.model.PlanetDetails
import com.example.falcone.model.VehicleDetails
import retrofit2.http.GET

interface FalconService {

    @GET("/planets")
    suspend fun getPlanets(): Result<List<PlanetDetails>>

    @GET("/vehicles")
    suspend fun getVehicles(): Result<List<VehicleDetails>>



    companion object{
        const val BASE_URL = "https://findfalcone.herokuapp.com"
    }
}