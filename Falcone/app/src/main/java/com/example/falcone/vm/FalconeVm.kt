package com.example.falcone.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.falcone.model.PlanetVehicleDetail
import com.example.falcone.model.ResponseStatus
import com.example.falcone.model.VehicleDetails
import com.example.falcone.repository.FalconRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class FalconeVm @Inject constructor(private val falconRepository: FalconRepository): ViewModel() {
    fun onVehicleSelected(planetId: String, vehicleId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            falconRepository.updateSelectedVehicleForPlanet(planetId, vehicleId)
        }
    }

    private val _planetVehicleData = MutableLiveData<List<PlanetVehicleDetail>>()
    private val _vehicleData = MutableLiveData<List<VehicleDetails>>()
    val planetVehicleData: LiveData<List<PlanetVehicleDetail>> get() = _planetVehicleData
    val vehicleLiveData: LiveData<List<VehicleDetails>> get() = _vehicleData


    init {
        viewModelScope.launch(Dispatchers.IO) {
            falconRepository.getPlanets().collect {
                when (it.status) {
                    ResponseStatus.SUCCESS -> {
                        it.data?.distinctUntilChanged()?.collect { list ->
                            _planetVehicleData.postValue(list)
                        }
                    }
                    ResponseStatus.ERROR -> {

                    }
                    ResponseStatus.LOADING -> {

                    }
                }

            }
        }
        viewModelScope.launch(Dispatchers.IO) {

            falconRepository.getVehicles().distinctUntilChanged().collect {
                falconRepository.getVehicles().collect {
                    when (it.status) {
                        ResponseStatus.SUCCESS -> {
                            it.data?.distinctUntilChanged()?.collect { list ->
                                _vehicleData.postValue(list)
                            }
                        }
                        ResponseStatus.ERROR -> {

                        }
                        ResponseStatus.LOADING -> {

                        }
                    }
                }
            }
        }
    }

}