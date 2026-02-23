package com.example.phonepe.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phonepe.Injection
import com.example.phonepe.repository.Repo

class ViewModelFactory private constructor(private val repo: Repo): ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) -> MainViewModel(repo)
                else -> throw IllegalArgumentException("Unknown view model class ${modelClass.name}")
            }
        } as T


    companion object{

        private var INSTANCE : ViewModelFactory? = null

        fun getInstance(app: Application): ViewModelFactory? {
            if(INSTANCE == null)
                INSTANCE =
                    ViewModelFactory(Injection.provideRepository(app))

            return INSTANCE
        }

    }

}