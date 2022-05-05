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

    private fun searchRecursive(file: File) {
        if (!file.exists()) {
            throw IllegalStateException("file does not exists: ${file.absolutePath}")
        }

        if (file.isDirectory) {
            val children = file.listFiles() ?: throw IllegalStateException("child file does not exists ${file.absolutePath}")

            if (children.none { it.isDirectory }) {
                directoryList.add(file)
            }

            children.forEach {
                searchRecursive(it)
            }
        }
    }
}
