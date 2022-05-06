package com.zh.file.browser.repository

import com.google.common.collect.ArrayListMultimap
import com.zh.file.browser.mapper.FbDirectoryMapper
import com.zh.file.browser.model.FbDirectory
import com.zh.file.browser.model.JoinedDirectory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class FbDirectoryRepository {

    @Autowired
    private lateinit var fbDirectoryMapper: FbDirectoryMapper

    fun list(): List<FbDirectory> {
        val allJoining: List<JoinedDirectory> = fbDirectoryMapper.listAllJoining()

        val multiMap = ArrayListMultimap.create<String, JoinedDirectory>()

        allJoining.forEach {
            multiMap.put(it.directoryId!!, it)
        }

        val fbDirectoryList = allJoining
            .distinctBy { it.directoryId }
            .map { it.toFbDirectory() }

        fbDirectoryList.forEach {
            it.images = multiMap[it.directoryId!!].filter { it.imageId != null }.distinctBy { it.imageId }.map { it.toFbImage() }
            it.videos = multiMap[it.directoryId!!].filter { it.videoId != null }.distinctBy { it.videoId }.map { it.toFbVideo() }
            it.castings = multiMap[it.directoryId!!].filter { it.castingId != null }.distinctBy { it.castingId }.map { it.toFbCasting() }
            it.genres = multiMap[it.directoryId!!].filter { it.genreId != null }.distinctBy { it.genreId }.map { it.toFbGenre() }
        }

        return fbDirectoryList
    }
}
