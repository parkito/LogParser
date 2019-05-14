package ru.siksmfp.kotlin.log.parser.dispatcher.impl.timer

import ru.siksmfp.kotlin.log.parser.dispatcher.api.TimerDispatcher

class TableConverterTimerDispatcher : TimerDispatcher {
    private companion object {
        private const val DB_TIMER = "TABLE_CONVERT_DATA_TIMER"
        private const val TIMER_INDEX_FROM = 84
    }

    override fun getTimerName(): String {
        return DB_TIMER
    }

    override fun getTimeIndexFrom(): Int {
        return TIMER_INDEX_FROM
    }
}