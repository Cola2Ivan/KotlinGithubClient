package com.example.common

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Preference<T>(val context: Context, val name: String, val default: T, val prefName: String = "defalut") :
    ReadWriteProperty<Any?, T> {

    private val prefs by lazy {
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }


    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getPrefs(name)
    }

    private fun getPrefs(key: String):T{
        return with(prefs){
            when(default){
                is Long -> getLong(key,default)
                is Int -> getInt(key,default)
                is Boolean -> getBoolean(key,default)
                is String -> getString(key ,default)
                is Float -> getFloat(key ,default)
                else -> throw IllegalArgumentException("unSupported type")
            }
        } as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPrefs(name,value)
    }

    private fun putPrefs(key: String, value:T){
        with(prefs.edit()){
            when(value){
                is Long -> putLong(key, value)
                is Int -> putInt(key,value)
                is Boolean -> putBoolean(key,value)
                is String -> putString(key ,value)
                is Float -> putFloat(key ,value)
                else -> throw IllegalArgumentException("unSupported type")
            }
        }.apply()
    }

}