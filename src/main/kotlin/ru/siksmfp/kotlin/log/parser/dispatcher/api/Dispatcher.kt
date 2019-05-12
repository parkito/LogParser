package ru.siksmfp.kotlin.log.parser.dispatcher.api

interface Dispatcher {

    fun processString(line: String): String?

    fun getName(): String {
        return this::class.java.name
    }
}
