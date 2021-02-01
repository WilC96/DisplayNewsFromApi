package com.example.musiclist.mvp;

import com.example.musiclist.model.MusicResult;
import com.example.musiclist.rest.ApiClient;
import com.example.musiclist.rest.ApiInterface;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MusicListPresenter implements IViewPresenterContract.IPresenter{
    private IViewPresenterContract.IView iView;

    public MusicListPresenter(IViewPresenterContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void retrieveData() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        apiService.getMusicDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MusicResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MusicResult musicResult) {
                        iView.passDataAdapter(musicResult);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
