package com.example.newsappmvvm.repo

import com.example.newsappmvvm.api.ApiInterface
import com.example.newsappmvvm.api.RetrofitClient
import com.example.newsappmvvm.model.NewsModel
import retrofit2.Response

class NewsRepository {

//    suspend fun getTopNews(param: Map<String, String>): Response<NewsModel> {
//        return RetrofitClient.getNewsService(ApiInterface::class.java).getTopNewsList(param)
//    }

    suspend fun getTopNews(): Response<NewsModel> {
        return RetrofitClient.getNewsService(ApiInterface::class.java).getTopNewsList()
    }

}