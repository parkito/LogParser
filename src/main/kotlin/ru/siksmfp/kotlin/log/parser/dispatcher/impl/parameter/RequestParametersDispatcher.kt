package ru.siksmfp.kotlin.log.parser.dispatcher.impl.parameter

class RequestParametersDispatcher : ru.siksmfp.kotlin.log.parser.dispatcher.api.Dispatcher {

    override fun processString(line: String): String? {
        val indexOfProcess = line.indexOf("retrieval processed:")
        if (indexOfProcess != -1) {
            return line.substring(indexOfProcess + 21, line.length)
        }
        return null
    }
}