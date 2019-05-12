package ru.siksmfp.kotlin.log.parser.dispatch.api

interface Dispatcher {

    fun processString(line: String): String?

    fun getName(): String {
        return this::class.java.name
    }
}
