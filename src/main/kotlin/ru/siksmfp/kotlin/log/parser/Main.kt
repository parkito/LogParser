package ru.siksmfp.kotlin.log.parser

import org.springframework.boot.autoconfigure.SpringBootApplication
import ru.siksmfp.kotlin.log.parser.dispatch.impl.RetrieveRequestParametersDispatcher

@SpringBootApplication
open class Main

fun main(args: Array<String>) {
    val logFilePath = "/Users/parkito/Downloads/logs_exp.txt"
    val reportFilePath = "/Users/parkito/Downloads/report.txt"
    val dispatchers = listOf(RetrieveRequestParametersDispatcher())

    DispatchPlan(logFilePath, reportFilePath, dispatchers)
            .executePlan()
}
