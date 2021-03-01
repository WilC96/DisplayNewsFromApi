package com.example.newsappmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappmvvm.R
import com.example.newsappmvvm.databinding.NewsRowBinding
import com.example.newsappmvvm.model.Article
import com.example.newsappmvvm.model.NewsModel

class TopNewsAdapter(private val listener: NewsClick): RecyclerView.Adapter<NewsViewHolder>() {

   //var news: NewsModel? = null

    var news: List<Article> = emptyList()
        //this "set" is a convenience method for vars, similar to a setter in java
        set(value) {
            field = value
            //updates the list every time it is set, and notifies observers when changed;
            //also invalidates every item in the recyclerview list
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val withDataBinding: NewsRowBinding = DataBindingUtil.inflate(
            // pass in the LayoutInflater class
            LayoutInflater.from(parent.context),
            // companion object from NewsViewHolder to provide the CONST layout
            NewsViewHolder.LAYOUT,
            // view group arg
            parent,
            false
        )
        return NewsViewHolder(withDataBinding)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        // defines the current news object
        holder.newsRowBinding.also {

            // take the articles from the news object and provide to the XML ROW binding
            it.article = news!![position]
            it.clickCallback = listener
        }
    }
}


class NewsViewHolder(val newsRowBinding: NewsRowBinding):
    RecyclerView.ViewHolder(newsRowBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.news_row
    }
}

class NewsClick(val block: (Article)->Unit) {
    // refers to it.article = articleList[position]
    fun onClick(article: Article) = block(article)
}