package com.zh.file.browser.mapper

import com.zh.file.browser.AbstractDbTest
import com.zh.file.browser.model.FbImageTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class FbImageMapperTest : AbstractDbTest {

    @Autowired
    private lateinit var sut: FbImageMapper

    @Test
    fun `insert and select test`() {
        // insert test
        val fbImage = FbImageTest.mockOne()
        sut.insertOne(fbImage)

        // select test
        val inserted = sut.listImageByDirectoryId("EFGH")
        assertThat(inserted).hasSize(1)
        assertThat(inserted.first().imagePath).isEqualTo("/some/image/path")
    }
}
