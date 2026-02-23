package com.example.falcone.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.falcone.di.ActivityScope
import com.example.falcone.di.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VmModule {

    @Binds
    @ApplicationScope
    internal abstract fun bindVMFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FalconeVm::class)
    internal abstract fun bindFalconeVm(vm: FalconeVm): ViewModel

}