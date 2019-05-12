package ru.siksmfp.kotlin.log.parser.dispatch.impl

import ru.siksmfp.kotlin.log.parser.dispatch.api.TimerDispatcher

class ReadTableDirectCallDispatcher : TimerDispatcher {
    private companion object {
        private const val DB_TIMER = "READ_TABLE_DIRECT_CALL"
        private const val TIMER_INDEX_FROM = 82
    }

    override fun getTimerName(): String {
        return DB_TIMER
    }

    override fun getTimeIndexFrom(): Int {
        return TIMER_INDEX_FROM
    }
}