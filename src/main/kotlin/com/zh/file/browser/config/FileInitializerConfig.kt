package com.zh.file.browser.config

import com.zh.file.browser.config.service.FileBrowser
import com.zh.file.browser.config.service.LeafDirectoryAnalyzer
import com.zh.file.browser.ffmpeg.VideoAnalyser
import com.zh.file.browser.mapper.FbDirectoryMapper
import com.zh.file.browser.mapper.FbVideoMapper
import com.zh.file.browser.model.FbDirectory
import com.zh.file.browser.model.FbVideo
import com.zh.file.browser.model.LeafDirectory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.io.File
import javax.annotation.PostConstruct

@Profile("!dao")
@Configuration
class FileInitializerConfig {

    @Autowired
    private lateinit var projectProperties: ProjectProperties
    @Autowired
    private lateinit var fbDirectoryMapper: FbDirectoryMapper
    @Autowired
    private lateinit var fbVideoMapper: FbVideoMapper

    @PostConstruct
    fun init() {
        val rootDirectory = File(projectProperties.rootPath!!)
        val leafDirectoryList = FileBrowser(rootDirectory).getDirectoryList()
        val analysedList = leafDirectoryList.map {
            LeafDirectoryAnalyzer(it).analysis()
        }

        analysedList.forEach {
            val fbDirectory = fbDirectoryMapper.getOne(it.getId())
            if (fbDirectory != null) {
                update(it, fbDirectory)
            } else {
                insert(it)
            }
        }

        // DB 입력
        // 만약 비디오 분석이 안되어있으면 분석기 돌리고 DB upsert
        // JTree 에 데이터 입력
    }

    private fun update(leafDirectory: LeafDirectory, fbDirectory: FbDirectory) {
    }

    private fun insert(leafDirectory: LeafDirectory) {
        val fbDirectory = FbDirectory(
            directoryId = leafDirectory.getId(),
            path = leafDirectory.path,
            description = leafDirectory.analyseTxtFile()?.description
        )
        fbDirectoryMapper.insertOne(fbDirectory)

        leafDirectory.videoFiles
            .map { videoFile ->
                VideoAnalyser(projectProperties.ffprobePath!!, videoFile).getInfo()
            }
            .map { videoInfo ->
                FbVideo(
                    videoId = null,
                    directoryId = fbDirectory.directoryId,
                    videoPath = videoInfo.path,
                    width = videoInfo.width,
                    height = videoInfo.height,
                    fps = videoInfo.fps,
                    codec = videoInfo.codec,
                    avgBitrate = videoInfo.avgBitrate.toString(),
                    runningTime = videoInfo.runningTime,
                    fileSize = videoInfo.fileSize
                )
            }
            .forEach { fbVideo ->
                fbVideoMapper.insertOne(fbVideo)
            }
    }
}
