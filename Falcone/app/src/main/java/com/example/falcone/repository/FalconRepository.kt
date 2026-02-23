package com.example.falcone.repository

import com.example.falcone.model.*
import com.example.falcone.repository.local.LocalRepository
import com.example.falcone.repository.remote.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FalconRepository @Inject constructor(private val localRepository: LocalRepository,
                                           private val remoteRepository: RemoteRepository) {

    fun getPlanets(): Flow<Resource<Flow<List<PlanetVehicleDetail>>, GenericErrorResponse>>  = flow{
        emit(Resource.loading())
        remoteRepository.getPlanets()
            .onSuccess {  data ->
                localRepository.insertPlanets(data)
                emit(Resource(ResponseStatus.SUCCESS, data = localRepository.getPlanetVehicleDetails()))

            }
            .onFailure {
                emit(Resource(ResponseStatus.ERROR, errorResponse =  GenericErrorResponse(it)))
            }
    }

    fun getVehicles(): Flow<Resource<Flow<List<VehicleDetails>>, GenericErrorResponse>>  = flow{
        emit(Resource.loading())
        remoteRepository.getVehicles()
            .onSuccess {  data ->
                localRepository.insertVehicles(data)
                emit(Resource(ResponseStatus.SUCCESS, data = localRepository.getVehicles()))

            }
            .onFailure {
                emit(Resource(ResponseStatus.ERROR, errorResponse =  GenericErrorResponse(it)))
            }
    }

    fun getPlanetVehicleDetails() =
        localRepository.getPlanetVehicleDetails()

    suspend fun updateSelectedVehicleForPlanet(planetId: String, vehicleId: String): Boolean{
        return localRepository.updateSelectedVehicleForPlanet(planetId, vehicleId)
    }
}