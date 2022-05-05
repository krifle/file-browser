package com.zh.file.browser.config.service

import com.zh.file.browser.model.LeafDirectory
import java.io.File

class LeafDirectoryAnalyzer(
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

    fun analysis(): LeafDirectory {
        if (! directory.isDirectory) {
            throw IllegalStateException("${directory.absolutePath} is not a directory.")
        }
        val txtFile: File? = getTxtFile()

        return LeafDirectory(
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
        return try {
            children.first { it.extension.lowercase() == "txt" }
        } catch (e: NoSuchElementException) {
            null
        }
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
