package com.zh.file.browser.mapper

import com.zh.file.browser.AbstractDbTest
import com.zh.file.browser.model.FbGenreTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class FbGenreMapperTest : AbstractDbTest {

    @Autowired
    private lateinit var sut: FbGenreMapper

    @Test
    fun `insert and select test`() {
        // insert test
        val fbGenre = FbGenreTest.mockOne()
        sut.insertOne(fbGenre)

        // select test
        val inserted = sut.listGenreByDirectoryId("ZXCZV")
        assertThat(inserted).hasSize(1)
        assertThat(inserted.first().genreName).isEqualTo("some genre")
        assertThat(inserted.first().directoryId).isEqualTo("ZXCZV")
    }
}
