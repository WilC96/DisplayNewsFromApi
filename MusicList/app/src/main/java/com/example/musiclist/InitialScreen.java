package com.example.musiclist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InitialScreen extends AppCompatActivity {

    Button initBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);

        initBtn = findViewById(R.id.btnToMain);

        initBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showMusicList();
            }
        });
    }

    public void showMusicList() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}