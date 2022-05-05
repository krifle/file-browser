package com.zh.file.browser.ffmpeg

import org.slf4j.LoggerFactory
import org.zeroturnaround.exec.ProcessExecutor
import java.io.OutputStream

class ExecuteUtils {
    companion object {
        private val logger = LoggerFactory.getLogger(ExecuteUtils::class.java)
    }

    fun execute(commandList: List<String>, outputStream: OutputStream): String {
        logger.debug("Running command >> {}", commandList.joinToString(separator = " "))

        try {
            return ProcessExecutor()
                .command(commandList)
                .redirectErrorStream(true)
                .redirectOutput(outputStream)
                .readOutput(true)
                .exitValueNormal()
                .destroyOnExit()
                .executeNoTimeout()
                .outputUTF8()
        } catch (e: Exception) {
            val command = commandList.joinToString(" ")
            logger.error("Failed for {}", command)
            throw IllegalStateException(">> $command")
        }
    }
}
