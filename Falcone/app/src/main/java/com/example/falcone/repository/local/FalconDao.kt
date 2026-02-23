package com.example.falcone.repository.local

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.example.falcone.model.PlanetDetails
import com.example.falcone.model.PlanetVehicleDetail
import com.example.falcone.model.VehicleDetails
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FalconDao {
    @Insert(onConflict = IGNORE)
    abstract fun insertPlanets(planets: List<PlanetDetails>)

    @Insert(onConflict = REPLACE)
    abstract suspend fun insertVehicles(vehicles: List<VehicleDetails>)

    @Query("SELECT * FROM planets WHERE name = :planetName")
    abstract suspend fun getPlanet(planetName: String): PlanetDetails?

    @Query("SELECT * FROM planets")
    abstract fun getAllPlanets(): Flow<List<PlanetDetails>>

    @Query("SELECT * FROM vehicles WHERE name = :vehicleName")
    abstract suspend fun getVehicle(vehicleName: String): VehicleDetails?

    @Query("SELECT * FROM vehicles")
    abstract fun getAllVehicles(): Flow<List<VehicleDetails>>

    @Transaction
    @Query("SELECT * FROM planets ORDER BY name")
    abstract fun getPlanetVehicleDetails(): Flow<List<PlanetVehicleDetail>>

    @Update
    abstract suspend fun updatePlanet(planetDetails: PlanetDetails)


    suspend fun updateSelectedVehicleForPlanet(planetId: String, vehicleId: String): Boolean{
        val planet = getPlanet(planetId)
        if(null == planet || null == getVehicle(vehicleId))
            return false
        planet.selectedVehicleId = vehicleId
        updatePlanet(planet)
        return true
    }


}