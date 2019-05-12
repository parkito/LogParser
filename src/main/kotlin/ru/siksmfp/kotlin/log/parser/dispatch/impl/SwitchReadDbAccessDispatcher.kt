package ru.siksmfp.kotlin.log.parser.dispatch.impl

import ru.siksmfp.kotlin.log.parser.dispatch.api.Dispatcher
import java.util.concurrent.TimeUnit


class SwitchReadDbAccessDispatcher : Dispatcher {
    private val DB_TIMER = "SWITCH_READ_DB_ACCESS_TIMER"

    override fun processString(line: String): String? {
        val indexOfTimer = line.indexOf(DB_TIMER)
        if (indexOfTimer != -1) {
            val nanosecs = line.substring(indexOfTimer + 87, line.length - 2).toLong()
            val millisecs = TimeUnit.MILLISECONDS.convert(nanosecs, TimeUnit.NANOSECONDS)
            return "$DB_TIMER = $millisecs"
        }
        return null
    }

    override fun getName(): String {
        return "SwitchReadDbAccessDispatcher"
    }
}