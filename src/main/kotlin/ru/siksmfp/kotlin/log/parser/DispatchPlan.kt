package ru.siksmfp.kotlin.log.parser

import ru.siksmfp.kotlin.log.parser.dispatch.api.Dispatcher
import java.io.BufferedReader
import java.io.RandomAccessFile
import java.nio.file.Files
import java.nio.file.Paths

class DispatchPlan(
        private val logFilePath: String,
        private val outputPath: String,
        private val dispatchers: List<Dispatcher>) {

    private val dispatchIterator = DispatchIterator(dispatchers)
    private val chanel = RandomAccessFile(outputPath, "rw").channel
    private val bufReader = BufferedReader(Files.newBufferedReader(Paths.get(logFilePath)))
    private var dispatcher = dispatchIterator.getFirst()

    fun executePlan() {
        while (bufReader.ready()) {
            val line = bufReader.readLine()
            val result = dispatchIterator.processLine(line)
            if (result != null) {
                chanel.write(result)
            }
        }
    }
}