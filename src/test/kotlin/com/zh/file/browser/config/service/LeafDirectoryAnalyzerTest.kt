package com.zh.file.browser.config.service

import com.zh.file.browser.TempFileTestPrep
import com.zh.file.browser.model.LeafDirectory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

internal class LeafDirectoryAnalyzerTest {

    @TempDir
    private lateinit var tempDir: File

    @Test
    fun analysisTest() {
        // given
        TempFileTestPrep.copySamplesToTemp(tempDir)
        val testDirectory = File(tempDir, "0BwRXrQIUBo")

        // when
        val result: LeafDirectory = LeafDirectoryAnalyzer(testDirectory).analysis()

        // then
        assertThat(result.txtFile).isNotNull()
        assertThat(result.imageFiles).hasSize(1)
        assertThat(result.imageFiles.first().name).isEqualTo("0BwRXrQIUBo.jpeg")
        assertThat(result.videoFiles).hasSize(1)
        assertThat(result.videoFiles.first().name).isEqualTo("0BwRXrQIUBo.mp4")
    }
}
