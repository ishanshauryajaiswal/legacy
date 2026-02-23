package com.example.phonepe

import android.app.Application
import com.example.phonepe.repository.LocalDataSource
import com.example.phonepe.repository.RemoteDataSource
import com.example.phonepe.repository.Repo
import com.example.phonepe.repository.RepoImpl

object Injection{

    fun provideRepository(app: Application) : Repo {
        return RepoImpl.getInstance(provideRemoteDataSource(app), provideLocalDataSource(app))
    }

    private fun provideRemoteDataSource(app: Application) = RemoteDataSource(app)

    private fun provideLocalDataSource(app: Application) = LocalDataSource(app)
}