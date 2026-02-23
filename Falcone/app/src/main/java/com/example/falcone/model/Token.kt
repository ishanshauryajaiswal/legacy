package com.example.falcone.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Token(@SerializedName("token") val token: String): Serializable