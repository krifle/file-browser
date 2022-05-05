package com.zh.file.browser.model

internal class FbThumbnailTest {
    companion object {
        fun mockOne(): FbThumbnail{
            return FbThumbnail(
                thumbnailId = null,
                videoId = -100,
                thumbnailPath = "/some/thumbnail/path"
            )
        }
    }
}
