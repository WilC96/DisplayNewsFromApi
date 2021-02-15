package com.example.flowerforallmvvm.view

import com.example.flowerforallmvvm.model.FlowerModel

interface FlowersListener {
    fun onItemClick(cakeModel: FlowerModel)
    fun onFailure(message: String)
}