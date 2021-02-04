package com.example.kotlinmusic.util

import android.view.View

interface IItemClickListener {
    fun onClick(
        view: View?,
        position: Int,
        isLongClick: Boolean
    )
}