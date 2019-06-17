package com.example.mvp

import android.content.res.Configuration
import android.os.Bundle

interface ILifecycle {

    fun onCreate(savedInstanceState: Bundle?)

    fun onSaveInstanceState(outState: Bundle)

    fun onViewStateRestore(savedInstanceState: Bundle?)

    fun onConfigurationChanged(newConfig: Configuration)

    fun onRestoreInstanceState(savedInstanceState: Bundle?)

    fun onDestory()

    fun onStart()

    fun onStop()

    fun onResume()

    fun onPause()

}