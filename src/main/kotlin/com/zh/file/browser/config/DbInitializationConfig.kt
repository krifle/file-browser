package com.zh.file.browser.config

import com.zh.file.browser.mapper.DbInitMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class DbInitializationConfig {

    @Autowired
    private lateinit var dbInitMapper: DbInitMapper

    @PostConstruct
    fun initDb() {
        dbInitMapper.initFbDirectory()
        dbInitMapper.initFbVideo()
        dbInitMapper.initFbImage()
        dbInitMapper.initFbCasting()
        dbInitMapper.initFbGenre()
        dbInitMapper.initFbThumbnail()
    }
}
