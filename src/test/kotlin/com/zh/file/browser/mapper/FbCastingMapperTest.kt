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

    @Test
    fun `listAllDistinctActors`() {
        // given
        val castings = listOf(
            FbCastingTest.mockOne("_directoryId1", "_actor1"),
            FbCastingTest.mockOne("_directoryId1", "_actor2"),
            FbCastingTest.mockOne("_directoryId2", "_actor3"),
            FbCastingTest.mockOne("_directoryId2", "_actor1"),
            FbCastingTest.mockOne("_directoryId3", "_actor5"),
            FbCastingTest.mockOne("_directoryId4", "_actor5")
        )
        castings.forEach { sut.insertOne(it) }

        // when
        val actors = sut.listAllDistinctActors()

        // then
        assertThat(actors).containsAnyOf("_actor1", "_actor2", "_actor3", "_actor4")
    }
}
