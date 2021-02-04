package com.example.kotlinmusic.util

import rx.Subscription
import rx.subscriptions.CompositeSubscription

class RxUtils {
    fun unsubscribeIfNotNull(subscription: Subscription?) {
        subscription?.unsubscribe()
    }

    fun getNewCompositeSubIfUnsubscribed(subscription: CompositeSubscription?): CompositeSubscription? {
        return if (subscription == null || subscription.isUnsubscribed) {
            CompositeSubscription()
        } else subscription
    }
}