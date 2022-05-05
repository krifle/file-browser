package com.zh.file.browser.config.service

import com.zh.file.browser.TempFileTestPrep
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

internal class FileBrowserTest {

    @TempDir
    private lateinit var tempDir: File

    @Test
    fun `gets directory list`() {
        // given
        TempFileTestPrep.copySamplesToTemp(tempDir)
        val root = tempDir

        // when
        val result = FileBrowser(root).getDirectoryList()

        // then
        result.forEach { println(it.absolutePath) }
    }
}
