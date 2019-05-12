package ru.siksmfp.kotlin.log.parser.dispatcher.api

interface StarterDispatcher : Dispatcher {

    override fun processString(line: String): String? {
        val indexOfParameter = line.indexOf(getInitParameter())
        if (indexOfParameter != -1) {
            return "${getMessage()} ${incrementAndGetCounter()}"
        }
        return null
    }

    fun getInitParameter(): String

    fun getMessage(): String

    fun incrementAndGetCounter(): Int
}