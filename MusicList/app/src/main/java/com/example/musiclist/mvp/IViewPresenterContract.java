package com.example.musiclist.mvp;

import com.example.musiclist.model.MusicModel;

import java.util.List;

public interface IViewPresenterContract {

    // presenter and view
    interface IPresenter extends IBasePresenter {
        // calls that the view can call
        void retrieveData();

    }


    interface IView extends IBaseView<IPresenter> {
        // calls that the presenter can call
        void passDataAdapter(MusicModel music);
    }
}
