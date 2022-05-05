package com.zh.file.browser.config.service

import com.zh.file.browser.TempFileTestPrep
import org.apache.commons.io.FilenameUtils
import org.assertj.core.api.Assertions.assertThat
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

        assertThat(result).hasSize(5)
        val directoryId = result.map { FilenameUtils.getName(it.absolutePath) }
        assertThat(directoryId).containsExactlyInAnyOrder(
            "0BwRXrQIUBo",
            "Vo9BwF4k3yw",
            "ebSR2mWzU3w",
            "m8wUO6XGJH8",
            "p8lOzNlCr78"
        )
    }
}
