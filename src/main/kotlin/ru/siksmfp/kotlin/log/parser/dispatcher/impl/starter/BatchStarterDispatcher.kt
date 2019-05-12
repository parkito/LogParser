package ru.siksmfp.kotlin.log.parser.dispatcher.impl.starter

import ru.siksmfp.kotlin.log.parser.dispatcher.api.StarterDispatcher

class BatchStarterDispatcher : StarterDispatcher {
    private companion object {
        private const val BATCH_INITIALIZATION_PARAMETER = "SWITCH retrieval request initiated"
        private const val BATCH_MESSAGE = "Switch batch request "
        private var counter = 0
    }

    override fun getInitParameter(): String {
        return BATCH_INITIALIZATION_PARAMETER
    }

    override fun getMessage(): String {
        return BATCH_MESSAGE
    }

    override fun incrementAndGetCounter(): Int {
        return ++counter
    }
}