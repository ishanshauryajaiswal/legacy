package com.example.phonepe.repository

import androidx.lifecycle.LiveData
import com.example.phonepe.model.Logo
import com.example.phonepe.model.Resource

interface Repo {

    fun getData(): LiveData<Resource<List<Logo>>>

}
