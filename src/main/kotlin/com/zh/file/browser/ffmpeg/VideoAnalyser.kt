package com.zh.file.browser.ffmpeg

import com.google.gson.Gson
import org.slf4j.LoggerFactory
import org.zeroturnaround.exec.stream.slf4j.Slf4jInfoOutputStream
import java.io.File

class VideoAnalyser(
    private val ffprobePath: String,
    private val inputFile: File,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(VideoAnalyser::class.java)
    }

    fun getInfo(): VideoInfo {
        val commandList = listOf(
            ffprobePath,
            "-v", "quiet",
            "-print_format", "json",
            "-show_format",
            "-show_streams",
            inputFile.absolutePath
        )

        try {
            val resultJson = ExecuteUtils().execute(commandList, Slf4jInfoOutputStream(logger))
            val result = Gson().fromJson(resultJson, FfprobeResult::class.java)
            val videoResult = result.streams.first { it.codecType.lowercase() == "video" }

            return VideoInfo(
                width = videoResult.width,
                height = videoResult.height,
                fps = videoResult.fps,
                codec = videoResult.codec,
                avgBitrate = videoResult.avgBitrate.toLong(),
                runningTime = videoResult.duration.toDouble().toLong(),
                fileSize = inputFile.length()
            )
        } catch (e: java.lang.NumberFormatException) {
            logger.error("Illegal number convertion for {}", commandList.joinToString(" "))
            throw IllegalStateException(e)
        } catch (e: Exception) {
            logger.error(">> ${commandList.joinToString(" ")}")
            throw IllegalStateException(e)
        }
    }
}
