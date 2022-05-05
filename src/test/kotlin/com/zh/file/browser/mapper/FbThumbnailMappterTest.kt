package com.zh.file.browser.mapper

import com.zh.file.browser.AbstractDbTest
import com.zh.file.browser.model.FbThumbnailTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class FbThumbnailMappterTest : AbstractDbTest {

    @Autowired
    private lateinit var sut: FbThumbnailMapper

    @Test
    fun `insert and list test`() {
        // insert
        val fbThumbnail = FbThumbnailTest.mockOne()
        sut.insertOne(fbThumbnail)

        // list
        val inserted = sut.listThumbnailsByVideoId(-100)
        assertThat(inserted).hasSize(1)
        assertThat(inserted.first().videoId).isEqualTo(-100)
        assertThat(inserted.first().thumbnailPath).isEqualTo("/some/thumbnail/path")
    }
}
