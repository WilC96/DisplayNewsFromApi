package com.example.newsappmvvm.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {
    fun main(work: suspend (()->Unit)) {
        //Dispatchers (is similar to Scheduler in RxJava)
        CoroutineScope(Dispatchers.Main).launch {
            //work that you want to do inside a coroutine
            work()
        }
    }
}