package com.zh.file.browser.mapper

import com.google.gson.Gson
import com.zh.file.browser.AbstractDbTest
import com.zh.file.browser.model.FbVideoTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class FbVideoMapperTest : AbstractDbTest {

    @Autowired
    private lateinit var sut: FbVideoMapper

    @Test
    fun `insert and select test`() {
        // insert test
        val fbVideo = FbVideoTest.mockOne()
        sut.insertOne(fbVideo)

        // select test
        val inserted = sut.listVideoByDirectoryId("ABCD")

        // then
        println(Gson().toJson(inserted))
        assertThat(inserted).hasSize(1)
        assertThat(inserted.first().directoryId).isEqualTo("ABCD")
    }
}
