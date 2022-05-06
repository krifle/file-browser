package com.zh.file.browser.model

data class FbDirectory(
    var directoryId: String? = null,
    var path: String? = null,
    var description: String? = null,

    var images: List<FbImage>? = null,
    var videos: List<FbVideo>? = null,
    var castings: List<FbCasting>? = null,
    var genres: List<FbGenre>? = null
)
