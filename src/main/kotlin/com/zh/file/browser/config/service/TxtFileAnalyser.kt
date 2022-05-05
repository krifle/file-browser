package com.zh.file.browser.config.service

import java.io.File

class TxtFileAnalyser(
    txtFile: File
) {
    private val txtLines = txtFile.readLines()
    private var currentMode = Mode.INIT

    private var idLine: String? = null
    private var actorListLine: String? = null
    private var genreListLine: String? = null
    private var descriptionLines: String = ""

    fun analysis(): TxtAnalysisResult {
        if (currentMode != Mode.INIT) {
            throw IllegalStateException("duplicated method call is not allowed.")
        }

        txtLines.forEachIndexed { index, line ->
            if (!line.trim().isBlank()) {
                currentMode = currentMode.getNextMode()
                if (currentMode == Mode.ID) {
                    idLine = line.trim()
                }
                if (currentMode == Mode.ACTORS) {
                    actorListLine = line.trim()
                }
                if (currentMode == Mode.GENRES) {
                    genreListLine = line.trim()
                }
                if (currentMode == Mode.DESCRIPTION) {
                    descriptionLines += line.trim()
                    if (index != txtLines.size - 1) {
                        descriptionLines += "\n"
                    }
                }
            }
        }

        return TxtAnalysisResult(
            id = idLine!!.trim(),
            actorList = actorListLine!!.split(",").map { it.trim() },
            genreList = genreListLine!!.split(",").map { it.trim() },
            description = descriptionLines ?: ""
        )
    }

    private enum class Mode(val order: Int) {
        INIT(0),
        ID(1),
        ACTORS(2),
        GENRES(3),
        DESCRIPTION(4);

        fun getNextMode(): Mode {
            if (this == DESCRIPTION) {
                return DESCRIPTION
            }

            val nextOrder = order + 1
            return values().first { it.order == nextOrder }
        }
    }
}
