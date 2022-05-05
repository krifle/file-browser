package com.zh.file.browser.model

internal class FbGenreTest {
    companion object {
        fun mockOne(): FbGenre {
            return FbGenre(
                genreId = null,
                directoryId = "ZXCZV",
                genreName = "some genre"
            )
        }

        fun mockOne(directoryId: String, genreName: String): FbGenre {
            return FbGenre(
                genreId = null,
                directoryId = directoryId,
                genreName = genreName
            )
        }
    }
}
