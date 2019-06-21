package com.example.common.log

import java.util.logging.Logger

val loggerMap = HashMap<Class<*>,Logger>()

//inline val <reified T> T.logger:Logger
//    get() {
//        return loggerMap[T::class.java]?:LoggerFactory.get
//    }