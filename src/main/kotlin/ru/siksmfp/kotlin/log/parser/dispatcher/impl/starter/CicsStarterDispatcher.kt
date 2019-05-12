package ru.siksmfp.kotlin.log.parser.dispatcher.impl.starter

import ru.siksmfp.kotlin.log.parser.dispatcher.api.StarterDispatcher

class CicsStarterDispatcher : StarterDispatcher {
    private companion object {
        private const val CICS_INITIALIZATION_PARAMETER = "SWITCH CICS update request initiated"
        private const val CICS_MESSAGE = "Cisc request"
        private var counter = 0
    }

    override fun getInitParameter(): String {
        return CICS_INITIALIZATION_PARAMETER
    }

    override fun getMessage(): String {
        return CICS_MESSAGE
    }

    override fun incrementAndGetCounter(): Int {
        return ++counter
    }
}