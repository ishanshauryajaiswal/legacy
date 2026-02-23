package com.example.phonepe.model

import com.google.gson.annotations.SerializedName


data class Logo (@SerializedName("imgUrl")val imageUrl: String?,
                 @SerializedName("name")val name:String?)