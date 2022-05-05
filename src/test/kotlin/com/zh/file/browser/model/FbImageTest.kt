package com.zh.file.browser.model

internal class FbImageTest {

    companion object {
        fun mockOne(): FbImage {
            return FbImage(
                imageId = null,
                directoryId = "EFGH",
                imagePath = "/some/image/path"
            )
        }
    }
}
