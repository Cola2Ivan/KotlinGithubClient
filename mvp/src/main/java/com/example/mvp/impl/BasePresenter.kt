package com.example.mvp.impl

import android.content.res.Configuration
import android.os.Bundle
import com.example.mvp.IMvpView
import com.example.mvp.IPresenter

abstract class BasePresenter<out V : IMvpView<BasePresenter<V>>> : IPresenter<V> {

    override lateinit var view: @UnsafeVariance V


    override fun onCreate(savedInstanceState: Bundle?) {
    }

    override fun onSaveInstanceState(outState: Bundle) {

    }

    override fun onViewStateRestore(savedInstanceState: Bundle?) {

    }

    override fun onConfigurationChanged(newConfig: Configuration) {

    }

    override fun onDestory() {

    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }
}