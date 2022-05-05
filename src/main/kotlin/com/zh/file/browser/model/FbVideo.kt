package com.zh.file.browser.model

data class FbVideo(
    var videoId: Int? = null,
    var directoryId: String? = null,
    var videoPath: String? = null,
    var width: Int? = null,
    var height: Int? = null,
    var fps: String? = null,
    var codec: String? = null,
    var avgBitrate: String? = null,
    var runningTime: Long? = null,
    var fileSize: Long? = null
)
