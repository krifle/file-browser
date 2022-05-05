package com.zh.file.browser.config

import com.zh.file.browser.config.service.DirectoryAnalyzer
import com.zh.file.browser.config.service.FileBrowser
import com.zh.file.browser.ffmpeg.VideoAnalyser
import com.zh.file.browser.mapper.FbDirectoryMapper
import com.zh.file.browser.model.FbDirectory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import java.io.File
import javax.annotation.PostConstruct

@Configuration
class FileInitializerConfig {

    @Autowired
    private lateinit var projectProperties: ProjectProperties
    @Autowired
    private lateinit var fbDirectoryMapper: FbDirectoryMapper

    @PostConstruct
    fun init() {
        val rootDirectory = File(projectProperties.rootPath!!)
        val directoryList = FileBrowser(rootDirectory).getDirectoryList()
        val analysedList = directoryList.map {
            return@map DirectoryAnalyzer(it).analysis()
        }

        analysedList.forEach {
            val fbDirectory = fbDirectoryMapper.getOne(it.getId())
            if (fbDirectory != null) {
                return@forEach
            }

            val videoInfo = VideoAnalyser(projectProperties.ffprobePath!!, File(it.path)).getInfo()
            FbDirectory(
                directoryId = it.getId(),
                path = it.path,
                description = it.analyseTxtFile()?.description
            )
        }

        // DB 입력
        // 만약 비디오 분석이 안되어있으면 분석기 돌리고 DB upsert
        // JTree 에 데이터 입력
    }
}
