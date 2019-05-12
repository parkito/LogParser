package ru.siksmfp.kotlin.log.parser.dispatch.impl

import ru.siksmfp.kotlin.log.parser.dispatch.api.Dispatcher
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit.NANOSECONDS

class SwitchReadOverAllTimerDispatcher : Dispatcher {
    companion object {
        private const val DB_TIMER = "SWITCH_READ_OVERALL_TIMER"
    }

    override fun processString(line: String): String? {
        val indexOfTimer = line.indexOf(DB_TIMER)
        if (indexOfTimer != -1) {
            val nanoSecs = line.substring(indexOfTimer + 85, line.length - 2).toLong()
            val milliSecs = MILLISECONDS.convert(nanoSecs, NANOSECONDS)
            return "$DB_TIMER = $milliSecs"
        }
        return null
    }
}