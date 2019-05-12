package ru.siksmfp.kotlin.log.parser.dispatcher.api

import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit.NANOSECONDS

interface TimerDispatcher : Dispatcher {
    private companion object {
        private const val TIMER_INDEX_TO = 2
    }

    override fun processString(line: String): String? {
        val indexOfTimer = line.indexOf(getTimerName())
        if (indexOfTimer != -1) {
            val nanoSecs = line.substring(indexOfTimer + getTimeIndexFrom(), line.length - TIMER_INDEX_TO).toLong()
            val milliSecs = MILLISECONDS.convert(nanoSecs, NANOSECONDS)
            return "${getTimerName()} = $milliSecs"
        }
        return null
    }

    fun getTimerName(): String

    fun getTimeIndexFrom(): Int
}
