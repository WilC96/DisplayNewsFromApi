package com.example.kotlinmusic.mvp

import com.example.kotlinmusic.model.MusicResult

interface IViewPresenterContract {
    // presenter and view
    interface IPresenter : IBasePresenter {
        // calls that the view can call
        fun retrieveData()
    }

    interface IView : IBaseView<IPresenter?> {
        // calls that the presenter can call
        fun passDataAdapter(music: MusicResult)
    }
}
