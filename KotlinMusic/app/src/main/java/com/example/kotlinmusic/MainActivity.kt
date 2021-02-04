package com.example.kotlinmusic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmusic.adapter.MusicAdapter
import com.example.kotlinmusic.model.MusicResult
import com.example.kotlinmusic.mvp.IViewPresenterContract
import com.example.kotlinmusic.mvp.MusicListPresenter
import kotlinx.android.synthetic.main.recycler_main.*


class MainActivity : AppCompatActivity(), IViewPresenterContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_main)

        val musicListPresenter = MusicListPresenter(this)
        initRecyclerView()
        musicListPresenter.retrieveData()
    }

    override fun passDataAdapter(music: MusicResult) {
        val musicAdapter =
            MusicAdapter(music, R.layout.music_row, applicationContext)
        rList.adapter = musicAdapter
    }

    override fun setPresenter(presenter: IViewPresenterContract.IPresenter?) {

    }

    private fun initRecyclerView() {
        rList.itemAnimator = DefaultItemAnimator()
        rList.layoutManager = LinearLayoutManager(this)
    }
}