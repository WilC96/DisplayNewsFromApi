package com.example.newsappmvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappmvvm.adapter.NewsClick
import com.example.newsappmvvm.adapter.TopNewsAdapter
import com.example.newsappmvvm.databinding.FragmentTopNewsBinding
import com.example.newsappmvvm.model.Article
import com.example.newsappmvvm.util.TopNewsListener
import com.example.newsappmvvm.viewmodel.TopNewsViewModel


class TopNewsFragment: Fragment(), TopNewsListener {

    private var _binding: FragmentTopNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this).get(TopNewsViewModel::class.java)
    }

    private lateinit var recyclerView: RecyclerView
    private var articlesAdapter: TopNewsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        for if you have a menu in the toolbar
//        setHasOptionsMenu(true)
    }

    // mostly used to initialise, or pass data between fragments using bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTopNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.lifecycleOwner = this
        binding.xmlViewModel = viewModel
        viewModel.topNewsListener = this

        // here lies the response (NewsModel), do stuff to it here
        viewModel.newsModel.observe(viewLifecycleOwner
        ) { news ->
            news?.let { it ->

                // Get the list from NewsModel
                val newsList = it.articles

                newsList?.let {
                    articlesAdapter?.news = it
                }
            }
        }

        articlesAdapter = TopNewsAdapter(NewsClick {
            // defines what the click on the cake does
            viewModel.displayArticleToast(it)
        })

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = articlesAdapter
    }


    override fun onItemClick(article: Article) {
        // this is associated with a single item clicked
        Toast.makeText(this.requireContext(), "Article written by " + article.author, Toast.LENGTH_LONG).show()
    }

    override fun onFailure(message: String) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_LONG).show()
    }

}