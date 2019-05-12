package ru.siksmfp.kotlin.log.parser

import ru.siksmfp.kotlin.log.parser.dispatch.api.Dispatcher
import java.nio.ByteBuffer

class DispatchIterator(private val dispatchers: List<Dispatcher>) {
    private val levelList = listOf("DEBUG", "INFO", "TRACE", "ERROR", "WARN")

    private val dispatchMap = HashMap<String, RequestContext>()

    fun processLine(line: String): ByteBuffer? {
        val request = getRequest(line) ?: return null

        var requestContext = dispatchMap[request]

        val currentDispatcher = if (requestContext == null) {
            requestContext = RequestContext(dispatchers)
            dispatchMap[request] = requestContext;
            dispatchers[0]
        } else {
            if (requestContext.isFinished()) {
                return null
            }
            dispatchers[requestContext.getCurrentDispatcher()]
        }

        val processResult = currentDispatcher.processString(line)
        if (processResult != null) {
            requestContext.addResult(currentDispatcher.getName(), processResult)
            requestContext.incrementDispatcher()

            if (requestContext.getCurrentDispatcher() >= dispatchers.size) {
                requestContext.finish()
                return requestContext.getReport()
            }
        }
        return null
    }

    private fun getRequest(line: String): String? {
        for (level in levelList) {
            val levelIndex = line.indexOf(level)
            if (levelIndex != -1) {
                return line.substring(levelIndex + 6, levelIndex + 40)
            }
        }
        return null
    }

}
