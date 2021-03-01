package com.example.newsappmvvm.api

import com.example.newsappmvvm.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("top-headlines?country=us&apiKey=3ad2ea1a69ad41a9b2f59fd8bc624dc8")
    suspend fun getTopNewsList(): Response<NewsModel>

//    @GET("top-headlines")
//    suspend fun getTopNewsList(@QueryMap param : Map<String, String>): Response<NewsModel>

}