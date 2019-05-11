package ru.siksmfp.kotlin.log.parser

import ru.siksmfp.kotlin.log.parser.dispatch.api.Dispatcher
import java.nio.ByteBuffer

class DispatchIterator(private val dispatchers: List<Dispatcher>) {

    private var counter: Int = 0
    private val dispatchMap = HashMap<String, RequestContext>()

    fun getFirst(): Dispatcher {
        return dispatchers[0]
    }

    fun next(): Dispatcher {
        counter++
        if (counter > dispatchers.size) {
            counter = 0
        }
        return dispatchers[counter]
    }

    fun processLine(line: String): ByteBuffer? {
        val request = getRequest(line)
        var requestContext = dispatchMap[request]

        val currentDispatcher = if (requestContext == null) {
            requestContext = RequestContext()
            dispatchMap[request] = requestContext;
            dispatchers[0]
        } else {
            dispatchers.get(requestContext.getCurrentDidpatecher())
        }

        val processResult = currentDispatcher.processString(line)
        if (processResult != null) {
            requestContext.addResult(processResult)
            requestContext.incrementDispatcher()

            if (requestContext.getCurrentDidpatecher() == dispatchers.size) {
                return requestContext.getReport()
            }
        }
        return null
    }

    private fun getRequest(line: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
