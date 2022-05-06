package com.zh.file.browser.repository

import com.google.common.collect.ArrayListMultimap
import com.google.gson.Gson
import com.zh.file.browser.model.JoinedDirectory
import org.junit.jupiter.api.Test

internal class FbDirectoryRepositoryTest {

    @Test
    fun `list 학습 테스트`() {
        // given
        val allJoining = listOf(
            JoinedDirectory(directoryId = "A", videoId = 1, imageId = 1, castingId = 1, genreId = 1),
            JoinedDirectory(directoryId = "A", videoId = 1, imageId = 1, castingId = 1, genreId = 2),
            JoinedDirectory(directoryId = "A", videoId = 1, imageId = 1, castingId = 2, genreId = 1),
            JoinedDirectory(directoryId = "A", videoId = 1, imageId = 1, castingId = 2, genreId = 2),
            JoinedDirectory(directoryId = "B", videoId = 2, imageId = 2, castingId = 3, genreId = 3),
            JoinedDirectory(directoryId = "B", videoId = 2, imageId = 2, castingId = 3, genreId = 4),
            JoinedDirectory(directoryId = "B", videoId = 2, imageId = 2, castingId = 3, genreId = 5),
            JoinedDirectory(directoryId = "C", videoId = 3, imageId = 3, castingId = 4, genreId = 6),
            JoinedDirectory(directoryId = "C", videoId = 3, imageId = 3, castingId = 4, genreId = 7),
            JoinedDirectory(directoryId = "C", videoId = 3, imageId = 3, castingId = 4, genreId = 8),
            JoinedDirectory(directoryId = "C", videoId = 3, imageId = 3, castingId = 4, genreId = 9),
            JoinedDirectory(directoryId = "C", videoId = 3, imageId = 3, castingId = 4, genreId = 10)
        )

        val multiMap = ArrayListMultimap.create<String, JoinedDirectory>()

        allJoining.forEach {
            val key = it.directoryId!!
            val value = it
            multiMap.put(key, value)
        }

        val fbDirectoryList = allJoining
            .distinctBy { it.directoryId }
            .map { it.toFbDirectory() }

        fbDirectoryList.forEach {
            it.images = multiMap[it.directoryId!!].distinctBy { it.imageId }.map { it.toFbImage() }
            it.videos = multiMap[it.directoryId!!].distinctBy { it.videoId }.map { it.toFbVideo() }
            it.castings = multiMap[it.directoryId!!].distinctBy { it.castingId }.map { it.toFbCasting() }
            it.genres = multiMap[it.directoryId!!].distinctBy { it.genreId }.map { it.toFbGenre() }
        }

        println(Gson().toJson(fbDirectoryList))
    }
}
