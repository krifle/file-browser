package com.zh.file.browser.mapper

import com.zh.file.browser.AbstractDbTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DbInitMapperTest : AbstractDbTest {

    @Autowired
    private lateinit var sut: DbInitMapper

    @Test
    fun initFbDirectory() {
        sut.initFbDirectory()
    }

    @Test
    fun initFbVideo() {
        sut.initFbVideo()
    }

    @Test
    fun initFbImage() {
        sut.initFbImage()
    }

    @Test
    fun initFbCasting() {
        sut.initFbCasting()
    }

    @Test
    fun initFbGenre() {
        sut.initFbGenre()
    }

    @Test
    fun initFbThumbnail() {
        sut.initFbThumbnail()
    }
}
