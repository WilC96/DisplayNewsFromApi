package com.example.kotlinmusic.rest

import com.example.kotlinmusic.model.MusicResult
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiInterface {
    @GET("/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50")
    fun getMusicDetails(): Observable<MusicResult>
}