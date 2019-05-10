package ru.siksmfp.kotlin.log.parser

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.siksmfp.kotlin.streams.interpreter.ParameterInterpreter
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths

class CreateArchiveTest {

    private val mainTestDir = "./mytest"
    private val archName = "./mytest.arch"
    private val extractParameter = "-e"
    private val extractArchiveDir = "./mytest2"

    private val mainTestDirPath = Paths.get(mainTestDir)
    private val extractedArchPath = Paths.get("./mytest2")
    private val archPath = Paths.get("./mytest.arch")

    private val dir1Dir = "$mainTestDir/dir1"
    private val dir2Dir = "$mainTestDir/test2"
    private val dir3Dir = "$mainTestDir/dir2/dir1"

    private val dir1 = Paths.get(dir1Dir)
    private val dir2 = Paths.get(dir2Dir)
    private val dir3 = Paths.get(dir3Dir)

    private val file1Dir = "$mainTestDir/dir1/file1"
    private val file2Dir = "$mainTestDir/dir1/file2"
    private val file3Dir = "$mainTestDir/test2/file3"
    private val file4Dir = "$mainTestDir/file4"
    private val file5Dir = "$mainTestDir/dir2/file5"

    private val file1 = Paths.get(file1Dir)
    private val file2 = Paths.get(file2Dir)
    private val file3 = Paths.get(file3Dir)
    private val file4 = Paths.get(file4Dir)
    private val file5 = Paths.get(file5Dir)

    private val file1Content = "File_1_Content"
    private val file2Content = "File_2_Content_!@#$%^&*()_"
    private val file3Content = ""
    private val file4Content = "123456"
    private val file5Content = "Content"
    private val file5ContentCount = 1000

    @BeforeEach
    fun createFilesForTest() {
        arrayOf(dir1, dir2, dir3).forEach { Files.createDirectories(it) }
        arrayOf(file1, file2, file3, file4, file5).forEach { Files.createFile(it) }

        writeCharacterFile(file1Dir, file1Content)
        writeBinaryFile(file2Dir, file2Content)
        writeCharacterFile(file3Dir, file3Content)
        writeBinaryFile(file4Dir, file4Content)
        writeManyStringsToFile(file5Dir, file5Content)
    }

    @AfterEach
    fun removeFiles() {
        Files.walk(mainTestDirPath)
                .sorted(reverseOrder())
                .forEach { Files.delete(it) }

        Files.walk(extractedArchPath)
                .sorted(reverseOrder())
                .forEach { Files.delete(it) }

        Files.delete(archPath);
    }

    @Test
    fun writeReadTest() {
        ParameterInterpreter(arrayOf(mainTestDir, archName)).run()
        ParameterInterpreter(arrayOf(extractParameter, archName, extractArchiveDir)).run()

        assertEquals(file1Content, Files.readString(file1))
        assertEquals(file2Content, Files.readString(file2))
        assertEquals(file3Content, Files.readString(file3))
        assertEquals(file4Content, Files.readString(file4))
        assertEquals(file5Content.length * file5ContentCount, Files.readString(file5).length)
    }

    private fun writeCharacterFile(path: String, content: String) {
        val fileWriter = FileWriter(path)
        val printWriter = PrintWriter(fileWriter)
        printWriter.printf(content)
        printWriter.close()
    }

    private fun writeBinaryFile(path: String, content: String) {
        val outputStream = FileOutputStream(path)
        val strToBytes = content.toByteArray()
        outputStream.write(strToBytes)

        outputStream.close()
    }

    private fun writeManyStringsToFile(path: String, contentToRepeat: String) {
        val fileWriter = FileWriter(path)
        val printWriter = PrintWriter(fileWriter)
        for (i in 0 until file5ContentCount) {
            printWriter.printf(contentToRepeat)
        }
        printWriter.close()
    }
}