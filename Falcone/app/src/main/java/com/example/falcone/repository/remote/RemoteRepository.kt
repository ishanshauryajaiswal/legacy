package com.example.falcone.repository.remote

import com.example.falcone.di.ApplicationScope
import com.example.falcone.model.PlanetDetails
import com.example.falcone.model.VehicleDetails
import javax.inject.Inject

@ApplicationScope
class RemoteRepository @Inject constructor(private val falconService: FalconService) {

    suspend fun getPlanets(): Result<List<PlanetDetails>> {
        return falconService.getPlanets()
    }

    suspend fun getVehicles(): Result<List<VehicleDetails>> {
        return falconService.getVehicles()
    }

}