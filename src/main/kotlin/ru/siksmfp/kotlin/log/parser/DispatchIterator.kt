package ru.siksmfp.kotlin.log.parser

import ru.siksmfp.kotlin.log.parser.dispatch.api.Dispatcher

class DispatchIterator(private val dispatchers: List<Dispatcher>) {

    private var counter: Int = 0

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

}
