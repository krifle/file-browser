package com.zh.file.browser.model

internal class FbVideoTest {

    companion object {
        fun mockOne(): FbVideo {
            return FbVideo(
                videoId = null,
                directoryId = "ABCD",
                videoPath = "/some/video/path.mp4",
                width = 400,
                height = 300,
                fps = "23.976",
                codec = "h.264",
                avgBitrate = "1000",
                runningTime = 1000L,
                fileSize = 2000L
            )
        }
    }
}
