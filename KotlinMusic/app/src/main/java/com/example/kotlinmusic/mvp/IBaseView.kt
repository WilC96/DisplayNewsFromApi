package com.example.kotlinmusic.mvp

interface IBaseView<T> {
    fun setPresenter(presenter: T)
}