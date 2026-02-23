package com.example.phonepe.repository

import androidx.lifecycle.LiveData
import com.example.phonepe.model.Logo
import com.example.phonepe.model.Resource

class RepoImpl(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource): Repo {

    companion object{
        private var INSTANCE: RepoImpl?=null

        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): Repo {
            if(INSTANCE == null)
                INSTANCE = RepoImpl(remoteDataSource, localDataSource)

            return INSTANCE!!
        }
    }



    override fun getData(): LiveData<Resource<List<Logo>>> {
        return localDataSource.getData()
    }


}