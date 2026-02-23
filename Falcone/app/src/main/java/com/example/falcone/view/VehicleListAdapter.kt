package com.example.falcone.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.falcone.databinding.ItemVehicleBinding
import com.example.falcone.model.VehicleDetails
import com.example.falcone.utils.planetDrawableList
import javax.inject.Inject

class VehicleListAdapter @Inject constructor(): ListAdapter<VehicleDetails, VehicleListAdapter.VehicleViewHolder>(diffCallback) {

    private var actionHandler: ActionHandler? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        return VehicleViewHolder(ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            actionHandler)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setActionHandler(actionHandler: ActionHandler){
        this.actionHandler = actionHandler
    }


    inner class VehicleViewHolder(private val binding: ItemVehicleBinding,
                                  private val actionHandler: ActionHandler?): RecyclerView.ViewHolder(binding.root){

        fun bind(vehicleDetails: VehicleDetails){
            binding.run{
                tvQuantity.text = vehicleDetails.totalNo.toString()
                tvSpeed.text = vehicleDetails.speed.toString()
                tvDistance.text = vehicleDetails.maxDistance.toString()
                tvVehicleName.text = vehicleDetails.name
                ivVehicle.setImageDrawable(ResourcesCompat.getDrawable(ivVehicle.context.resources, planetDrawableList.random(), null))
                binding.root.setOnClickListener {
                    actionHandler?.onVehicleClicked(vehicleDetails)
                }
            }
        }

    }

    interface ActionHandler{
        fun onVehicleClicked(vehicleDetails: VehicleDetails)
    }

    companion object{
        val diffCallback = object :DiffUtil.ItemCallback<VehicleDetails>(){
            override fun areItemsTheSame(oldItem: VehicleDetails, newItem: VehicleDetails): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: VehicleDetails, newItem: VehicleDetails): Boolean {
                return (oldItem == newItem)
            }

        }
    }
}