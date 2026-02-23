package com.example.falcone.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "planets")
data class PlanetDetails(@PrimaryKey @SerializedName("name") val name: String,
                         @SerializedName("distance") val distance: String,
                         var selectedVehicleId: String? = null): Serializable