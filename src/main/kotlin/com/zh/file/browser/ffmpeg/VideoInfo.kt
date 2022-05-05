package com.zh.file.browser.ffmpeg

data class VideoInfo(
    val width: Int,
    val height: Int,
    val fps: String,
    val codec: String,
    val avgBitrate: Long,
    val runningTime: Long
)
