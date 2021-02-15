package com.example.flowerforallmvvm.api

import com.example.flowerforallmvvm.model.FlowerModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("feeds/flowers.json")
    suspend fun getFlowerlist(): Response<List<FlowerModel>>
}