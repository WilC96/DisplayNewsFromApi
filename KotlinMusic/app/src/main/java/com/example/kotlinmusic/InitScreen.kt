package com.example.kotlinmusic

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinmusic.mvp.IViewPresenterShowListContract
import kotlinx.android.synthetic.main.activity_init_screen.*

class InitScreen : AppCompatActivity(), IViewPresenterShowListContract.IView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_screen)

        displayMusicChart(initBtn)
    }

    override fun displayMusicChart(btn: Button) {
        btn.setOnClickListener(View.OnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        })
    }

    override fun setPresenter(presenter: IViewPresenterShowListContract.IPresenter?) {

    }
}