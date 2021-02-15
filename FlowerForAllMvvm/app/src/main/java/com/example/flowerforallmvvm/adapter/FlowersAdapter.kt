package com.example.flowerforallmvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowerforallmvvm.R
import com.example.flowerforallmvvm.databinding.FlowerRowBinding
import com.example.flowerforallmvvm.model.FlowerModel
import kotlinx.android.synthetic.main.flower_row.view.*

class FlowersAdapter(val listener: FlowerClick): RecyclerView.Adapter<FlowersViewHolder>() {

    var flowersList: List<FlowerModel> = emptyList()
        //this "set" is a convenience method for vars, similar to a setter in java
        set(value) {
            field = value
            //updates the list every time it is set, and notifies observers when changed;
            //also invalidates every item in the recyclerview list
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowersViewHolder {
        val withDataBinding: FlowerRowBinding = DataBindingUtil.inflate(
            //takes a layoutInflater arg, which can be initiated using LayoutInflater class
            LayoutInflater.from(parent.context),
            //Int arg, meaning the id of the layout to be inflated
            FlowersViewHolder.LAYOUT,
            //viewgroup arg
            parent,
            false
        )
        return FlowersViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: FlowersViewHolder, position: Int) {
        // defines the current cake object
        holder.flowerRowBinding.also {
            it.flower = flowersList[position]
            it.clickCallback = listener
//            Glide.with(mContext)
//                .load("https://services.hanselandpetal.com/photos/" + it.flower.photo)
//                .into(it.imageViewFlower)
        }
    }

    override fun getItemCount(): Int {
        return if (flowersList == null) 0 else flowersList.size
    }
}

class FlowersViewHolder(val flowerRowBinding: FlowerRowBinding):
    RecyclerView.ViewHolder(flowerRowBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.flower_row
    }
}

class FlowerClick(val block: (FlowerModel)->Unit) {
    // refers to it.flower = flowersList[position]
    fun onClick(flower: FlowerModel) = block(flower)
}