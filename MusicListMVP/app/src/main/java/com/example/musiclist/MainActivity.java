package com.example.musiclist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.musiclist.adapter.MusicAdapter;
import com.example.musiclist.model.MusicModel;
import com.example.musiclist.model.MusicResult;
import com.example.musiclist.mvp.IViewPresenterContract;
import com.example.musiclist.mvp.MusicListPresenter;
import com.example.musiclist.rest.ApiClient;
import com.example.musiclist.rest.ApiInterface;
import com.example.musiclist.util.RxUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity implements IViewPresenterContract.IView {
    private MusicListPresenter musicListPresenter;
    private RecyclerView rView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);
        musicListPresenter = new MusicListPresenter(this);
        initRecyclerView();
        musicListPresenter.retrieveData();
    }

    @Override
    public void passDataAdapter(MusicResult music) {
        MusicAdapter musicAdapter = new MusicAdapter(music, R.layout.music_row, getApplicationContext());
        rView.setAdapter(musicAdapter);
    }

    @Override
    public void setPresenter(IViewPresenterContract.IPresenter presenter) {

    }

    private void initRecyclerView() {
        rView = (RecyclerView)findViewById(R.id.rList);
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setItemAnimator(new DefaultItemAnimator());
    }
}