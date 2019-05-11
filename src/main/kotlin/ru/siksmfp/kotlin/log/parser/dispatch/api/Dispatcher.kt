package ru.siksmfp.kotlin.log.parser.dispatch.api

import java.nio.ByteBuffer

interface Dispatcher {

    fun processString(line: String): String?

    fun getName(): String
}
