package ru.siksmfp.kotlin.log.parser.dispatch.impl

import ru.siksmfp.kotlin.log.parser.dispatch.api.TimerDispatcher

class SwitchReadOverAllTimerDispatcher : TimerDispatcher {
    private companion object {
        private const val DB_TIMER = "SWITCH_READ_OVERALL_TIMER"
        private const val TIMER_INDEX_FROM = 85
    }

    override fun getTimerName(): String {
        return DB_TIMER
    }

    override fun getTimeIndexFrom(): Int {
        return TIMER_INDEX_FROM
    }
}