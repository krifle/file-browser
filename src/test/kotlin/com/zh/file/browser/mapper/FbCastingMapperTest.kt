package com.zh.file.browser.mapper

import com.zh.file.browser.AbstractDbTest
import com.zh.file.browser.model.FbCastingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class FbCastingMapperTest : AbstractDbTest {

    @Autowired
    private lateinit var sut: FbCastingMapper

    @Test
    fun `insert and select test`() {
        // insert test
        val fbCasting = FbCastingTest.mockOne()
        sut.insertOne(fbCasting)

        // select test
        val inserted = sut.listCastingByDirectoryId("ASDF")
        assertThat(inserted).hasSize(1)
        assertThat(inserted.first().actorName).isEqualTo("some Actor")
        assertThat(inserted.first().directoryId).isEqualTo("ASDF")
    }
}
