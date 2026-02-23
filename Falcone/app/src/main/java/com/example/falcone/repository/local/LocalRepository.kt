package com.example.falcone.repository.local

import com.example.falcone.model.PlanetDetails
import com.example.falcone.model.VehicleDetails
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

class LocalRepository @Inject constructor(private val falconDao: FalconDao) {

    fun getPlanets(): Flow<List<PlanetDetails>> {
        return falconDao.getAllPlanets()
    }

    suspend fun insertPlanets(planets: List<PlanetDetails>): Boolean{
        return try {
            falconDao.insertPlanets(planets)
            true
        } catch (e: Exception){
            false
        }
    }

    fun getVehicles(): Flow<List<VehicleDetails>> {
        return falconDao.getAllVehicles()
    }

    suspend fun insertVehicles(vehicles: List<VehicleDetails>): Boolean{
        return try {
            falconDao.insertVehicles(vehicles)
            true
        } catch (e: Exception){
            false
        }
    }

    fun getPlanetVehicleDetails() =
        falconDao.getPlanetVehicleDetails()

    suspend fun updateSelectedVehicleForPlanet(planetId: String, vehicleId: String): Boolean{
        return falconDao.updateSelectedVehicleForPlanet(planetId, vehicleId)
    }

}