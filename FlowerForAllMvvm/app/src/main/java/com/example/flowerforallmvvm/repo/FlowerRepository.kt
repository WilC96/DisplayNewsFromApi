package com.example.flowerforallmvvm.repo

import com.example.flowerforallmvvm.api.ApiInterface
import com.example.flowerforallmvvm.api.RetrofitClient
import com.example.flowerforallmvvm.model.FlowerModel
import retrofit2.Response

class FlowerRepository {
    suspend fun getFlowers(): Response<List<FlowerModel>> {
        return RetrofitClient.getFlowerService(ApiInterface::class.java).getFlowerlist()
    }
}