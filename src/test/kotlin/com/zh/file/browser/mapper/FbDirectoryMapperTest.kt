package com.zh.file.browser.mapper

import com.google.gson.Gson
import com.zh.file.browser.AbstractDbTest
import com.zh.file.browser.model.FbDirectory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class FbDirectoryMapperTest : AbstractDbTest {

    @Autowired
    private lateinit var sut: FbDirectoryMapper

    @Test
    fun `insert and select test`() {
        // insert test
        val fbDirectory = FbDirectory("test-directory-id1", "/user/test-path", "test Description.")
        sut.insertOne(fbDirectory)

        // select test
        val inserted = sut.getOne("test-directory-id1")!!
        assertThat(inserted.directoryId).isEqualTo(fbDirectory.directoryId)
        assertThat(inserted.path).isEqualTo(fbDirectory.path)
        assertThat(inserted.description).isEqualTo(fbDirectory.description)
    }

    @Test
    fun `list all directory`() {
        // given // when
        val list = sut.listDirectory()

        // then
        println(Gson().toJson(list))
    }
}
