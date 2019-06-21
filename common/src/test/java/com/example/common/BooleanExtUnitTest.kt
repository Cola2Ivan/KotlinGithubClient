package com.example.common

import com.example.common.ext.otherwise
import com.example.common.ext.yes
import org.junit.Test


class BooleanExtUnitTest {

    @Test
    fun BooleanExtTest(){

        val result = getBoolean().yes{
            1
        }.otherwise{
            2
        }
        print(result)
    }

    fun getBoolean() = true
}