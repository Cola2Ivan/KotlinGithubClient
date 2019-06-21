package com.example.githubclient.utils

import com.example.common.sharedprefences.Preference
import com.example.githubclient.AppContext
import kotlin.reflect.jvm.jvmName

inline fun <reified R,T> R.pref(default: T) = Preference(AppContext,"",default,R::class.jvmName)