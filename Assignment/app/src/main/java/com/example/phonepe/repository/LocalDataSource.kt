package com.example.phonepe.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.phonepe.model.Logo
import com.example.phonepe.model.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.Error
import java.nio.charset.Charset


class LocalDataSource(private val appContext: Context){

    fun getData(): LiveData<Resource<List<Logo>>>{

        val data = MutableLiveData<Resource<List<Logo>>>()
        data.value = Resource.loading()

        var json: String? = null
        try {
            val inputStream = appContext.assets.open("logo.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))

            val gson = Gson()
            val turnsType = object : TypeToken<List<Logo>>() {}.type
            val result = gson.fromJson<List<Logo>>(json, turnsType)
            data.value = Resource.success(result)

        } catch (e: IOException) {
            e.printStackTrace()
            data.value = Resource.error(Error())
        }

        return data

    }

}