package com.example.falcone.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.falcone.R
import com.example.falcone.di.AppComponentProvider
import com.example.falcone.model.PlanetVehicleDetail
import com.example.falcone.view.VehicleListBottomSheet.Companion.KEY_RESULT
import com.example.falcone.view.VehicleListBottomSheet.Companion.KEY_SELECTED_PLANET
import com.example.falcone.view.VehicleListBottomSheet.Companion.KEY_VEHICLE_ID
import com.example.falcone.view.VehicleListBottomSheet.Companion.TAG
import com.example.falcone.vm.FalconeVm
import javax.inject.Inject

class PlanetDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var planetAdapter: PlanetListAdapter
    private val vm: FalconeVm by viewModels {viewModelFactory}
    private val rvPlanets by lazy { findViewById<RecyclerView>(R.id.rvPlanets) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppComponentProvider.getInstance(application).activityComponent().inject(this)
        setupView()
        setupObservers()
    }

    private fun setupObservers() {

        vm.planetVehicleData.observe(this, { data ->
            planetAdapter.submitList(data)
        })
    }

    private fun setupView() {
        setContentView(R.layout.activity_main)
        rvPlanets.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = planetAdapter.also {
                it.setActionHandler(actionHandlerListener)
            }
        }
        startActivity(Intent(this, DeleteActivity::class.java))
    }

    private val actionHandlerListener = object : PlanetListAdapter.ActionHandler {
        override fun onPlanetClicked(planetVehicleDetail: PlanetVehicleDetail) {
            VehicleListBottomSheet.newInstance(planetVehicleDetail.planetDetails.name)
                .show(supportFragmentManager, TAG)
            supportFragmentManager.setFragmentResultListener(KEY_RESULT, this@PlanetDetailActivity,
                { key, result ->
                    if (key == KEY_RESULT) {
                        vm.onVehicleSelected(
                            result.getString(KEY_SELECTED_PLANET)!!,
                            result.getString(KEY_VEHICLE_ID)!!
                        )
                    }
                })
        }
    }

}