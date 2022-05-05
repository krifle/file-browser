package com.zh.file.browser.config

import com.zh.file.browser.config.service.FileBrowser
import com.zh.file.browser.config.service.LeafDirectoryAnalyzer
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
        val leafDirectoryList = FileBrowser(rootDirectory).getDirectoryList()
        val analysedList = leafDirectoryList.map {
            return@map LeafDirectoryAnalyzer(it).analysis()
        }

        analysedList.forEach {
            val fbDirectory = fbDirectoryMapper.getOne(it.getId())
            if (fbDirectory != null) {
                // 파일들의 내용을 업데이트 한다.
                return@forEach
            }

            // 파일들의 내용을 insert 한다. ffmpeg 도 돌려서 분석 한다.
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
