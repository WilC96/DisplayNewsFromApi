package com.example.flowerforallmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flowerforallmvvm.model.FlowerModel
import com.example.flowerforallmvvm.repo.FlowerRepository
import com.example.flowerforallmvvm.util.Coroutines
import com.example.flowerforallmvvm.view.FlowersListener

class FlowerViewModel: ViewModel() {

    var flowersList = MutableLiveData<List<FlowerModel>>()
    var flowersListener: FlowersListener? = null

    init {
        refreshList()
    }

    fun refreshList() {
        Coroutines.main {
            val cakesResponse = FlowerRepository().getFlowers()
            if (cakesResponse.isSuccessful) {
                cakesResponse.body()?.let {
                    flowersList.value = it
                    Log.d("cakes_list", flowersList.toString())
                }
                    .run {
                        // flowersList.value = emptyList() why was this here
                    }
            } else {
                flowersListener?.onFailure(cakesResponse.message())
            }
        }
    }

    fun displayFlowerToast(flowerModel: FlowerModel) {
        flowersListener?.onItemClick(flowerModel)
    }
}