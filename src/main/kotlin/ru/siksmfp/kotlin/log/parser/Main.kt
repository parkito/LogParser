package ru.siksmfp.kotlin.log.parser

import org.springframework.boot.autoconfigure.SpringBootApplication
import ru.siksmfp.kotlin.log.parser.dispatcher.impl.parameter.RetrieveRequestParametersDispatcher
import ru.siksmfp.kotlin.log.parser.dispatcher.impl.timer.ReadTableDirectCallTimerDispatcher
import ru.siksmfp.kotlin.log.parser.dispatcher.impl.timer.SwitchReadDbAccessTimerDispatcher
import ru.siksmfp.kotlin.log.parser.dispatcher.impl.timer.SwitchReadOverAllTimerDispatcher
import ru.siksmfp.kotlin.log.parser.execution.DispatchPlan

@SpringBootApplication
open class Main

fun main(args: Array<String>) {
    val logFilePath = "/Users/parkito/Downloads/logs_exp.txt"
    val reportFilePath = "/Users/parkito/Downloads/report.txt"
    val dispatchers = listOf(
            ReadTableDirectCallTimerDispatcher(),
            RetrieveRequestParametersDispatcher(),
            SwitchReadDbAccessTimerDispatcher(),
            SwitchReadOverAllTimerDispatcher()
    )

    DispatchPlan(logFilePath, reportFilePath, dispatchers)
            .executePlan()
}


//Retrieval
// ReadTableDirectCallTimerDispatcher -> RetrieveRequestParametersDispatcher -> SwitchReadDbAccessTimerDispatcher -> SwitchReadOverAllTimerDispatcher
//
//CISC Retrieval
//ReadTableDirectCallTimerDispatcher -> RetrieveRequestParametersDispatcher -> SwitchReadDbAccessTimerDispatcher -> SwitchCiscOverAllTimerDispatcher
//
