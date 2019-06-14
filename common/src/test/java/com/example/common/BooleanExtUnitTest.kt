package com.example.common

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