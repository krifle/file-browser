package com.zh.file.browser.config.service

import com.zh.file.browser.config.ProjectProperties
import com.zh.file.browser.ffmpeg.VideoAnalyser
import com.zh.file.browser.mapper.*
import com.zh.file.browser.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LeafDirectoryManager {

    @Autowired
    private lateinit var projectProperties: ProjectProperties
    @Autowired
    private lateinit var fbDirectoryMapper: FbDirectoryMapper
    @Autowired
    private lateinit var fbVideoMapper: FbVideoMapper
    @Autowired
    private lateinit var fbImageMapper: FbImageMapper
    @Autowired
    private lateinit var fbGenreMapper: FbGenreMapper
    @Autowired
    private lateinit var fbCastingMapper: FbCastingMapper

    @Transactional
    fun insert(leafDirectory: LeafDirectory) {
        val fbDirectory = FbDirectory(
            directoryId = leafDirectory.getId(),
            path = leafDirectory.path,
            description = leafDirectory.analyseTxtFile()?.description
        )

        insertFbVideos(leafDirectory)
        insertFbImages(leafDirectory)

        leafDirectory.analyseTxtFile()?.let { analysisResult ->
            fbDirectory.description = analysisResult.description
            insertGenreList(leafDirectory.getId(), analysisResult)
            insertCastingList(leafDirectory.getId(), analysisResult)
        }

        fbDirectoryMapper.insertOne(fbDirectory)
    }

    private fun insertFbVideos(leafDirectory: LeafDirectory) {
        leafDirectory.videoFiles
            .map { videoFile ->
                VideoAnalyser(projectProperties.ffprobePath!!, videoFile).getInfo()
            }
            .map { videoInfo ->
                FbVideo(
                    videoId = null,
                    directoryId = leafDirectory.getId(),
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

    private fun insertFbImages(leafDirectory: LeafDirectory) {
        leafDirectory.imageFiles
            .map {  imageFile ->
                FbImage(
                    imageId = null,
                    directoryId = leafDirectory.getId(),
                    imagePath = imageFile.absolutePath
                )
            }
            .forEach { fbImage ->
                fbImageMapper.insertOne(fbImage)
            }
    }

    private fun insertGenreList(directoryId: String, analysisResult: TxtAnalysisResult) {
        analysisResult.genreList
            .forEach { genre ->
                val fbGenre = FbGenre(
                    genreId = null,
                    directoryId = directoryId,
                    genreName = genre
                )
                fbGenreMapper.insertOne(fbGenre)
            }
    }

    private fun insertCastingList(directoryId: String, analysisResult: TxtAnalysisResult) {
        analysisResult.actorList
            .forEach { actor ->
                val fbCasting = FbCasting(
                    castingId = null,
                    directoryId = directoryId,
                    actorName = actor
                )
                fbCastingMapper.insertOne(fbCasting)
            }
    }

    @Transactional
    fun update(leafDirectory: LeafDirectory, fbDirectory: FbDirectory) {
    }
}
