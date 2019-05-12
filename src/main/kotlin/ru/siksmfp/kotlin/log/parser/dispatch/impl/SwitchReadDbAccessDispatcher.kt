package ru.siksmfp.kotlin.log.parser.dispatch.impl

import ru.siksmfp.kotlin.log.parser.dispatch.api.TimerDispatcher

class SwitchReadDbAccessDispatcher : TimerDispatcher {
    private companion object {
        private const val DB_TIMER = "SWITCH_READ_DB_ACCESS_TIMER"
        private const val TIMER_INDEX_FROM = 87
    }

    override fun getTimerName(): String {
        return DB_TIMER
    }

    override fun getTimeIndexFrom(): Int {
        return TIMER_INDEX_FROM
    }
}