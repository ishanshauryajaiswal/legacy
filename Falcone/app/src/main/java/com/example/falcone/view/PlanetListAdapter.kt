package com.example.falcone.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.falcone.databinding.ItemPlanetBinding
import com.example.falcone.model.PlanetVehicleDetail
import com.example.falcone.utils.planetDrawableList
import javax.inject.Inject
import kotlin.math.roundToInt

class PlanetListAdapter @Inject constructor(): ListAdapter<PlanetVehicleDetail, PlanetListAdapter.PlanetViewHolder>(diffCallback) {

    private var actionHandler: ActionHandler? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        return PlanetViewHolder(ItemPlanetBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            actionHandler)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setActionHandler(actionHandler: ActionHandler){
        this.actionHandler = actionHandler
    }


    inner class PlanetViewHolder(private val binding: ItemPlanetBinding,
                                 private val actionHandler: ActionHandler?): RecyclerView.ViewHolder(binding.root){

        fun bind(planetVehicleDetail: PlanetVehicleDetail){
            binding.run{
                tvPlanetName.text = planetVehicleDetail.planetDetails.name
                tvPlanetDistance.text = planetVehicleDetail.planetDetails.distance
                ivPlanet.setImageDrawable(ResourcesCompat.getDrawable(ivPlanet.context.resources, planetDrawableList.random(), null))
                ivPlanet.setOnClickListener {
                    actionHandler?.onPlanetClicked(planetVehicleDetail)
                }
                planetVehicleDetail.selectedVehicleDetails?.let{
                    tvVehicleName.text = it.name
                    tvTime.text = (planetVehicleDetail.planetDetails.distance.toDouble() / it.speed.toDouble()).roundToInt().toString()
                }
            }
        }

    }

    interface ActionHandler{
        fun onPlanetClicked(planetVehicleDetail: PlanetVehicleDetail)
    }

    companion object{
        val diffCallback = object :DiffUtil.ItemCallback<PlanetVehicleDetail>(){
            override fun areItemsTheSame(oldVehicleDetail: PlanetVehicleDetail, newVehicleDetail: PlanetVehicleDetail): Boolean {
                return oldVehicleDetail.planetDetails === newVehicleDetail.planetDetails
            }

            override fun areContentsTheSame(oldVehicleDetail: PlanetVehicleDetail, newVehicleDetail: PlanetVehicleDetail): Boolean {
                return (oldVehicleDetail.planetDetails.name == newVehicleDetail.planetDetails.name
                        && oldVehicleDetail.selectedVehicleDetails == newVehicleDetail.selectedVehicleDetails)
            }

        }
    }

}