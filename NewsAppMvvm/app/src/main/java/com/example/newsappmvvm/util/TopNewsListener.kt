package com.example.newsappmvvm.util

import com.example.newsappmvvm.model.Article

interface TopNewsListener {
    fun onItemClick(newsModel: Article)
    fun onFailure(message: String)
}