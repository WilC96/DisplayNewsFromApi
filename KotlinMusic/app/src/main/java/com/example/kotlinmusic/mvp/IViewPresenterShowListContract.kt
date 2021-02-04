package com.example.kotlinmusic.mvp

import android.content.Intent
import android.widget.Button
import com.example.kotlinmusic.model.MusicResult

interface IViewPresenterShowListContract {
    // presenter and view
    interface IPresenter : IBasePresenter {
        // calls that the view can call
        fun retrieveIntentData()
    }

    interface IView : IBaseView<IPresenter?> {
        // calls that the presenter can call
        fun displayMusicChart(btn: Button)
    }
}