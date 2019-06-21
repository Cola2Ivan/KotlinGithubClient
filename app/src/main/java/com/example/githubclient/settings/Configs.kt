package com.example.githubclient.settings

import android.util.Log
import com.example.githubclient.AppContext
import com.example.githubclient.utils.deviceId

object Configs {

    object Account {
        val SCOPES = listOf("user", "repo", "notifications", "gist", "admin:org")
        const val clientId = "123456"
        const val clientSecret = "123456"
        const val note = "kotliner.cn"
        const val noteUrl = "http://www.kotliner.cn"

        val fingerPrint by lazy {
            (AppContext.deviceId + clientId).also { Log.i("111", "fingerPrint:" + it) }
        }

    }
}

