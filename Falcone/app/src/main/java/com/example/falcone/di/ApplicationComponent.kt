package com.example.falcone.di

import android.app.Application
import com.example.falcone.vm.VmModule
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.sync.Mutex

@ApplicationScope
@Component(modules = [ApplicationModule::class, VmModule::class])
interface ApplicationComponent {
    @ActivityScope
    fun activityComponent(): ActivityComponent
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun applicationContext(applicationContext: Application): Builder
        fun build(): ApplicationComponent
    }
}

object AppComponentProvider{
    private var INSTANCE : ApplicationComponent? = null

    fun getInstance(applicationContext: Application): ApplicationComponent {
        val mutex = Mutex()
        return (INSTANCE ?: synchronized(mutex){
            if(INSTANCE == null){
                INSTANCE = DaggerApplicationComponent.builder().applicationContext(applicationContext).build()
            }
            INSTANCE!!
        })
    }
}