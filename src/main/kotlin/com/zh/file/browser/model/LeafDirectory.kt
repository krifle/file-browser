package com.zh.file.browser.model

import com.zh.file.browser.config.service.TxtAnalysisResult
import com.zh.file.browser.config.service.TxtFileAnalyser
import java.io.File

data class LeafDirectory(
    val path: String,
    val txtFile: File?,
    val imageFiles: List<File>,
    val videoFiles: List<File>
) {
    fun getId(): String {
        return File(path).name
    }

    fun analyseTxtFile(): TxtAnalysisResult? {
        if (txtFile == null) {
            return null
        }
        return TxtFileAnalyser(txtFile).analysis()
    }
}
