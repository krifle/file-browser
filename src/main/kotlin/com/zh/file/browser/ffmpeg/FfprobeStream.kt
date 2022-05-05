package com.zh.file.browser.ffmpeg

import com.google.gson.annotations.SerializedName

data class FfprobeStream(
    val index: Int,
    @SerializedName("codec_type") val codecType: String,
    @SerializedName("codec_name") val codec: String,
    val width: Int,
    val height: Int,
    @SerializedName("avg_frame_rate") val fps: String,
    @SerializedName("bit_rate") val avgBitrate: String,
    val duration: String
)
