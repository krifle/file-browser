package com.zh.file.browser.config.service

import java.io.File

class FileBrowser(
    private val rootDirectory: File
) {
    private val directoryList = mutableListOf<File>()

    fun getDirectoryList(): List<File> {
        searchRecursive(rootDirectory)
        return directoryList.toList()
    }

    private fun searchRecursive(f: File) {
        if (!f.exists()) {
            throw IllegalStateException("file does not exists: ${f.absolutePath}")
        }

        if (f.isDirectory) {
            directoryList.add(f)

            val children = f.listFiles() ?: throw IllegalStateException("child file does not exists ${f.absolutePath}")
            children.forEach {
                searchRecursive(it)
            }
        }
    }
}
