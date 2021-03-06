package com.zh.file.browser.model

internal class FbCastingTest {
    companion object {
        fun mockOne(): FbCasting {
            return FbCasting(
                castingId = null,
                directoryId = "ASDF",
                actorName = "some Actor"
            )
        }

        fun mockOne(directoryId: String, actorName: String): FbCasting {
            return FbCasting(
                castingId = null,
                directoryId = directoryId,
                actorName = actorName
            )
        }
    }
}
