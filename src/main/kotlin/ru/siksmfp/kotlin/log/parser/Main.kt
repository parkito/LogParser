package ru.siksmfp.kotlin.log.parser

import ru.siksmfp.kotlin.log.parser.dispatcher.impl.parameter.RequestParametersDispatcher
import ru.siksmfp.kotlin.log.parser.dispatcher.impl.starter.BatchStarterDispatcher
import ru.siksmfp.kotlin.log.parser.dispatcher.impl.starter.CicsStarterDispatcher
import ru.siksmfp.kotlin.log.parser.dispatcher.impl.timer.DataConverterTimerDispatcher
import ru.siksmfp.kotlin.log.parser.dispatcher.impl.timer.ReadDbAccessTimerDispatcher
import ru.siksmfp.kotlin.log.parser.dispatcher.impl.timer.ReadTableDirectCallTimerDispatcher
import ru.siksmfp.kotlin.log.parser.dispatcher.impl.timer.SwitchCiscOverAllTimerDispatcher
import ru.siksmfp.kotlin.log.parser.dispatcher.impl.timer.SwitchReadOverAllTimerDispatcher
import ru.siksmfp.kotlin.log.parser.dispatcher.impl.timer.TableConverterTimerDispatcher
import ru.siksmfp.kotlin.log.parser.execution.DispatchPlan

open class Main

fun main(args: Array<String>) {
    val logFilePath = "/Users/parkito/Downloads/1.txt"
    val reportFilePath = "/Users/parkito/Downloads/report.txt"
    val batchDispatchers = listOf(
            BatchStarterDispatcher(),
            ReadTableDirectCallTimerDispatcher(),
            TableConverterTimerDispatcher(),
            RequestParametersDispatcher(),
            DataConverterTimerDispatcher(),
            ReadDbAccessTimerDispatcher(),
            SwitchReadOverAllTimerDispatcher()
    )

    val cicsDispatchers = listOf(
            CicsStarterDispatcher(),
            ReadTableDirectCallTimerDispatcher(),
            RequestParametersDispatcher(),
            ReadDbAccessTimerDispatcher(),
            SwitchCiscOverAllTimerDispatcher()
    )

    DispatchPlan(logFilePath, reportFilePath, batchDispatchers)
            .executePlan()
}


//Retrieval
// ReadTableDirectCallTimerDispatcher -> RequestParametersDispatcher -> ReadDbAccessTimerDispatcher -> SwitchReadOverAllTimerDispatcher
//
//CISC Retrieval
//ReadTableDirectCallTimerDispatcher -> RequestParametersDispatcher -> ReadDbAccessTimerDispatcher -> SwitchCiscOverAllTimerDispatcher
//
