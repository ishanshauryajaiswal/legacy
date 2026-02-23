package com.example.falcone.di

import com.example.falcone.view.PlanetDetailActivity
import com.example.falcone.view.VehicleListBottomSheet
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ActivityComponent {
    fun inject(planetDetailActivity: PlanetDetailActivity)
    fun inject(vehicleListBottomSheet: VehicleListBottomSheet)
}