package com.example.falcone.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "vehicles")
data class VehicleDetails(@PrimaryKey @SerializedName("name") val name: String,
                          @SerializedName("total_no") val totalNo: Int,
                          @SerializedName("max_distance") val maxDistance: Int,
                          @SerializedName("speed") val speed: Int): Serializable