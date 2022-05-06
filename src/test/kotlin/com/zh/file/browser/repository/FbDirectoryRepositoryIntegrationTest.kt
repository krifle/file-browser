package com.zh.file.browser.repository

import com.google.gson.Gson
import com.zh.file.browser.AbstractDbTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class FbDirectoryRepositoryIntegrationTest : AbstractDbTest {

    @Autowired
    private lateinit var sut: FbDirectoryRepository

    @Test
    fun listAll() {
        // given // when
        val result = sut.list()

        // then
        println(Gson().toJson(result))
    }
}
