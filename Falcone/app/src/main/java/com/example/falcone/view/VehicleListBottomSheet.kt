package com.example.falcone.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.falcone.R
import com.example.falcone.di.AppComponentProvider
import com.example.falcone.model.VehicleDetails
import com.example.falcone.vm.FalconeVm
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class VehicleListBottomSheet: BottomSheetDialogFragment() {

    @Inject
    lateinit var vehicleListAdapter: VehicleListAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val rvVehicle by lazy { requireView().findViewById<RecyclerView>(R.id.rvVehicle) }
    private val vm by activityViewModels<FalconeVm>{viewModelFactory}

    private val actionHandlerListener = object : VehicleListAdapter.ActionHandler {
        override fun onVehicleClicked(vehicleDetails: VehicleDetails) {
            setFragmentResult(KEY_RESULT, Bundle().apply {
                putString(KEY_SELECTED_PLANET, arguments!!.getString(KEY_SELECTED_PLANET))
                putString(KEY_VEHICLE_ID, vehicleDetails.name)
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vehicle_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppComponentProvider.getInstance(requireActivity().application).activityComponent().inject(this)
        setupView()
        setupObservers()
        Log.d("ishan", "fac ${viewModelFactory.hashCode()}, vm - ${vm.hashCode()}")
    }

    private fun setupView(){
        rvVehicle.apply {
            adapter = vehicleListAdapter.also {
                it.setActionHandler(actionHandlerListener)
            }
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupObservers(){
        vm.vehicleLiveData.observe(viewLifecycleOwner, {data ->
            vehicleListAdapter.submitList(data)
            Log.d("ishan", data.size.toString())
        })
    }

    companion object{
        const val TAG = "VehicleListBottomSheet"
        const val KEY_RESULT = "resultVehicleListBottomSheet"
        const val KEY_VEHICLE_ID = "vehicleId"
        const val KEY_SELECTED_PLANET = "planetId"
        fun newInstance(selectedPlanetId: String): VehicleListBottomSheet{
            return VehicleListBottomSheet().apply {
                arguments = Bundle().also {
                    it.putString(KEY_SELECTED_PLANET, selectedPlanetId)
                }
            }
        }
    }

}