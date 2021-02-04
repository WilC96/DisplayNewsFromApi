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
/*
Steps for creating a project in MVP requiring network calls:

1. create the classes/objects for Retrofit client and API Service Call

2. create the necessary adapter classes if you're using recyclerview

3. create the necessary POJO/model classes

4. Create the Base classes for View and Presenter

5. Create a contract between the Base and Presenter. Do this for each view that has a different functionality

6. Implement the View and Presenter interfaces
*/