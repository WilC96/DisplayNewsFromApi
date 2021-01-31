package com.example.musiclist.rest;

import com.example.musiclist.model.MusicResult;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50")
    Observable<MusicResult> getMusicDetails();
}
