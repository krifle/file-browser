package com.zh.file.browser.model

data class JoinedDirectory(
    // fb_directory
    var directoryId: String? = null,
    var path: String? = null,
    var description: String? = null,

    //fb_video
    var videoId: Int? = null,
    var videoPath: String? = null,
    var width: Int? = null,
    var height: Int? = null,
    var fps: String? = null,
    var codec: String? = null,
    var avgBitrate: String? = null,
    var runningTime: Long? = null,
    var fileSize: Long? = null,

    // fb_image
    var imageId: Int? = null,
    var imagePath: String? = null,

    // fb_casting
    var castingId: Int? = null,
    var actorName: String? = null,

    // fb_genre
    var genreId: Int? = null,
    var genreName: String? = null
) {
    fun toFbDirectory(): FbDirectory {
        return FbDirectory(
            directoryId = directoryId,
            path = path,
            description = description
        )
    }

    fun toFbVideo(): FbVideo {
        return FbVideo(
            videoId = videoId,
            directoryId = directoryId,
            videoPath = videoPath,
            width = width,
            height = height,
            fps = fps,
            codec = codec,
            avgBitrate = avgBitrate,
            runningTime = runningTime,
            fileSize = fileSize
        )
    }

    fun toFbImage(): FbImage {
        return FbImage(
            imageId = imageId,
            directoryId = directoryId,
            imagePath = imagePath
        )
    }

    fun toFbCasting(): FbCasting {
        return FbCasting(
            castingId = castingId,
            directoryId = directoryId,
            actorName = actorName
        )
    }

    fun toFbGenre(): FbGenre {
        return FbGenre(
            genreId = genreId,
            directoryId = directoryId,
            genreName = genreName
        )
    }
}
