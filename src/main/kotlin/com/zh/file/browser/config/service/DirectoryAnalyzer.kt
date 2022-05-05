package com.zh.file.browser.config.service

import com.zh.file.browser.model.Directory
import java.io.File

class DirectoryAnalyzer(
    private val directory: File
) {
    companion object {

        private val wellKnownImageExtensions = listOf("gif", "jpg", "jpeg", "jfif", "pjpeg", "pjp",
            "png", ".svg", "webp", "bmp", "tif", "tiff", "apng", "avif"
        )

        private val wellKnownVideoExtensions = listOf("mp4", "mov", "wmv", "avi", "avchd", "flv",
            "f4v", "swf", "mkv", "webm", "html5", "vob", "qt", "asf", "mpg", "mp2", "mpeg", "mpe",
            "mpv", "m4v", "mxf"
        )
    }

    private val children = directory.listFiles()

    fun analysis(): Directory {
        if (! directory.isDirectory) {
            throw IllegalStateException("${directory.absolutePath} is not a directory.")
        }
        val txtFile = getTxtFile()

        return Directory(
            path = directory.absolutePath,
            txtFile = txtFile,
            imageFiles = getImageFiles(),
            videoFiles = getVideoFiles()
        )
    }

    private fun getTxtFile(): File? {
        if (children.isNullOrEmpty()) {
            return null
        }
        return children.first { it.extension.lowercase() == "txt" }
    }

    private fun getImageFiles(): List<File> {
        if (children.isNullOrEmpty()) {
            return listOf()
        }
        return children.filter { wellKnownImageExtensions.contains(it.extension.lowercase()) }
    }

    private fun getVideoFiles(): List<File> {
        if (children.isNullOrEmpty()) {
            return listOf()
        }
        return children.filter { wellKnownVideoExtensions.contains(it.extension.lowercase()) }
    }
}
