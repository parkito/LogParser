package ru.siksmfp.kotlin.log.parser.execution

import java.nio.ByteBuffer
import java.util.stream.Collectors

class RequestContext {
    private companion object {
        private const val NEW_STRING_SEPARATOR = "\n"
        private const val EMPTY = ""
    }

    private val dispatchResultMap = LinkedHashMap<String, String>()

    private var counter = 0;
    private var isFinished = false

    init {
        dispatchResultMap[EMPTY] = NEW_STRING_SEPARATOR
    }

    fun addResult(name: String, result: String) {
        dispatchResultMap[name] = result
    }

    fun getCurrentDispatcher(): Int {
        return counter
    }

    fun getReport(): ByteBuffer? {
        return ByteBuffer.wrap(
                dispatchResultMap.values
                        .stream()
                        .collect(Collectors.joining(NEW_STRING_SEPARATOR))
                        .toByteArray()
        )
    }

    fun incrementDispatcher() {
        counter++
    }

    fun isFinished(): Boolean {
        return isFinished
    }

    fun finish() {
        isFinished = true
    }
}
