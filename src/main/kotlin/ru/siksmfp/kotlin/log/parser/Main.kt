package ru.siksmfp.kotlin.log.parser

import org.springframework.boot.autoconfigure.SpringBootApplication
import ru.siksmfp.kotlin.log.parser.dispatch.impl.ReadTableDirectCallDispatcher
import ru.siksmfp.kotlin.log.parser.dispatch.impl.RetrieveRequestParametersDispatcher
import ru.siksmfp.kotlin.log.parser.dispatch.impl.SwitchReadDbAccessDispatcher
import ru.siksmfp.kotlin.log.parser.dispatch.impl.SwitchReadOverAllTimerDispatcher

@SpringBootApplication
open class Main

fun main(args: Array<String>) {
    val logFilePath = "/Users/parkito/Downloads/logs_exp.txt"
    val reportFilePath = "/Users/parkito/Downloads/report.txt"
    val dispatchers = listOf(
            ReadTableDirectCallDispatcher(),
            RetrieveRequestParametersDispatcher(),
            SwitchReadDbAccessDispatcher(),
            SwitchReadOverAllTimerDispatcher()
    )

    DispatchPlan(logFilePath, reportFilePath, dispatchers)
            .executePlan()
}
