package com.zh.file.browser.mapper

import org.apache.ibatis.annotations.Mapper

@Mapper
interface DbInitMapper {

    fun initFbDirectory()

    fun initFbVideo()

    fun initFbImage()

    fun initFbCasting()

    fun initFbGenre()

    fun initFbThumbnail()
}
