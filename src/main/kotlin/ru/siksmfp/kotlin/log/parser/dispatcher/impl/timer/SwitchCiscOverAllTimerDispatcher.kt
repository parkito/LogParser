package ru.siksmfp.kotlin.log.parser.dispatcher.impl.timer

import ru.siksmfp.kotlin.log.parser.dispatcher.api.TimerDispatcher

class SwitchCiscOverAllTimerDispatcher : TimerDispatcher {
    private companion object {
        private const val DB_TIMER = "SWITCH_CICS_OVERALL_TIMER"
        private const val TIMER_INDEX_FROM = 82 //todo finish
    }

    override fun getTimerName(): String {
        return DB_TIMER
    }

    override fun getTimeIndexFrom(): Int {
        return TIMER_INDEX_FROM
    }
}