package ru.siksmfp.kotlin.log.parser.dispatch.impl

class RetrieveRequestParametersDispatcher : ru.siksmfp.kotlin.log.parser.dispatch.api.Dispatcher {

    override fun processString(line: String): String? {
        val indexOfProcess = line.indexOf("retrieval processed:")
        if (indexOfProcess != -1) {
            return line.substring(indexOfProcess + 22, line.length)
        }
        return null
    }

    override fun getName(): String {
        return "RetrieveRequestParametersDispatcher"
    }
}