package com.example.falcone.model

import androidx.room.Embedded
import androidx.room.Relation

data class PlanetVehicleDetail(
    @Embedded val planetDetails: PlanetDetails,
    @Relation(
        parentColumn = "selectedVehicleId",
        entityColumn = "name"
    )
    val selectedVehicleDetails: VehicleDetails?
)