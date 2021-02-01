package com.example.musiclist.util;

import android.view.View;

public interface IItemClickListener {
    void onClick(View view, int position, boolean isLongClick);
}
