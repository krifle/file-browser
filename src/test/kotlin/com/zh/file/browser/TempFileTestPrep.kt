package com.zh.file.browser

import org.apache.commons.io.FileUtils
import java.io.File

class TempFileTestPrep {
    companion object {
        fun copySamplesToTemp(tempDir: File) {
            val resource = TempFileTestPrep::class.java.getResource("/sample/")
                ?: throw IllegalStateException("")
            val sampleDirectory = File(resource.file)

            FileUtils.copyDirectory(sampleDirectory, tempDir)
        }
    }
}
