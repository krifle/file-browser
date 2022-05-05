package com.zh.file.browser.ffmpeg

import com.google.gson.Gson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

internal class VideoAnalyserTest {

    @Test
    fun `ffprobe working test`() {
        // given
        val sampleVideoResource = javaClass.getResource("/sample/0BwRXrQIUBo/0BwRXrQIUBo.mp4") ?: throw IllegalStateException("")
        val sampleVideoFile = File(sampleVideoResource.file)
        val ffprobePath = "/opt/homebrew/bin/ffprobe"

        // when
        val result: VideoInfo = VideoAnalyser(ffprobePath, sampleVideoFile).getInfo()

        // then
        println(Gson().toJson(result))
        assertThat(result.width).isEqualTo(480)
        assertThat(result.height).isEqualTo(270)
        assertThat(result.fps).isEqualTo("60/1")
        assertThat(result.codec).isEqualTo("h264")
        assertThat(result.avgBitrate).isEqualTo(196296L)
        assertThat(result.runningTime).isEqualTo(30L)
        assertThat(result.fileSize).isEqualTo(1024904L)
    }
}

