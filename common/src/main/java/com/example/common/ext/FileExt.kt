package com.example.common.ext

import android.util.Log
import java.io.File
import java.lang.Exception

fun File.ensureDir():Boolean{
    try {
        isDirectory.no{
            isFile.yes {
                delete()
            }
            return mkdirs()
        }
    }catch ( e: Exception){
        e.printStackTrace();
        Log.w("11",e.localizedMessage)
    }
    return false;
}