package ru.siksmfp.kotlin.log.parser

import ru.siksmfp.kotlin.log.parser.dispatch.api.Dispatcher
import java.io.BufferedReader
import java.io.RandomAccessFile
import java.nio.channels.FileChannel
import java.nio.file.Files
import java.nio.file.Paths

class DispatchPlan(
        logFilePath: String,
        outputPath: String,
        dispatchers: List<Dispatcher>) {

    private val dispatchIterator: DispatchIterator = DispatchIterator(dispatchers)
    private val chanel: FileChannel = RandomAccessFile(outputPath, "rw").channel
    private val bufReader: BufferedReader = BufferedReader(Files.newBufferedReader(Paths.get(logFilePath)))

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