package com.example.kotlinmusic.mvp

import com.example.kotlinmusic.model.MusicResult
import com.example.kotlinmusic.rest.ApiClient
import com.example.kotlinmusic.rest.ApiInterface
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.create


class MusicListPresenter(private var iView: IViewPresenterContract.IView) :
    IViewPresenterContract.IPresenter {

    override fun retrieveData() {
        val apiService = ApiClient.getClient()?.create(ApiInterface::class.java)

        apiService?.getMusicDetails()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<MusicResult> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(musicResult: MusicResult) {
                    iView.passDataAdapter(musicResult)
                }

                override fun onError(e: Throwable) {}
                override fun onComplete() {}
            })
    }
}