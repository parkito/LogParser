package ru.siksmfp.kotlin.log.parser.dispatch.impl

import ru.siksmfp.kotlin.log.parser.dispatch.api.Dispatcher
import java.util.concurrent.TimeUnit

class SwitchReadOverAllTimerDispatcher : Dispatcher {
    private val DB_TIMER = "SWITCH_READ_OVERALL_TIMER"

    override fun processString(line: String): String? {
        val indexOfTimer = line.indexOf(DB_TIMER)
        if (indexOfTimer != -1) {
            val nanosecs = line.substring(indexOfTimer + 85, line.length - 2).toLong()
            val millisecs = TimeUnit.MILLISECONDS.convert(nanosecs, TimeUnit.NANOSECONDS)
            return "$DB_TIMER = $millisecs"
        }
        return null
    }

    override fun getName(): String {
        return "SwitchReadOverAllTimerDispatcher"
    }
}