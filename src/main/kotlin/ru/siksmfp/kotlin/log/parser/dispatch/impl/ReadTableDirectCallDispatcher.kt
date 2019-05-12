package ru.siksmfp.kotlin.log.parser.dispatch.impl

import ru.siksmfp.kotlin.log.parser.dispatch.api.Dispatcher
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit.NANOSECONDS

class ReadTableDirectCallDispatcher : Dispatcher {
    companion object {
        private const val DB_TIMER = "READ_TABLE_DIRECT_CALL"
    }

    override fun processString(line: String): String? {
        val indexOfTimer = line.indexOf(DB_TIMER)
        if (indexOfTimer != -1) {
            val nanoSecs = line.substring(indexOfTimer + 82, line.length - 2).toLong()
            val milliSecs = MILLISECONDS.convert(nanoSecs, NANOSECONDS)
            return "$DB_TIMER = $milliSecs"
        }
        return null
    }
}