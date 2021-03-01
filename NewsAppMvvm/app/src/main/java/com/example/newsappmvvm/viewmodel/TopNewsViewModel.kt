package com.example.newsappmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsappmvvm.adapter.NewsClick
import com.example.newsappmvvm.adapter.TopNewsAdapter
import com.example.newsappmvvm.api.RetrofitClient
import com.example.newsappmvvm.model.Article
import com.example.newsappmvvm.model.NewsModel
import com.example.newsappmvvm.repo.NewsRepository
import com.example.newsappmvvm.util.Coroutines
import com.example.newsappmvvm.util.TopNewsListener

class TopNewsViewModel : ViewModel() {

    var newsModel = MutableLiveData<NewsModel>()
    var topNewsListener: TopNewsListener? = null

    var param: MutableMap<String, String> = HashMap()

    init {
        param["country"] = "us"
        param["apiKey"] = RetrofitClient.API_KEY
        refreshList()

    }

    private fun refreshList() {
        Coroutines.main {
            // this way we can modify the url
//          val newsModelResponse = NewsRepository().getTopNews(param)

            // this is a concrete/sure fire way to execute the url but immutable. FOR TESTING
            val newsModelResponse = NewsRepository().getTopNews()
            if (newsModelResponse.isSuccessful) {
                newsModelResponse.body()?.let { it ->
                    newsModel.value = it

                    Log.d("cakes_list", newsModel.toString())
                }
                    .run {
                        // flowersList.value = emptyList() why was this here
                    }
            } else {
                topNewsListener?.onFailure(newsModelResponse.message())
            }
        }
    }

    fun displayArticleToast(newsModel: Article) {
        topNewsListener?.onItemClick(newsModel)
    }

}