package com.zh.file.browser.mapper

import com.zh.file.browser.model.FbDirectory
import com.zh.file.browser.model.JoinedDirectory
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface FbDirectoryMapper {

    @Select("""
        SELECT
            directory_id AS directoryId
            , path AS path
            , description AS description
        FROM
            fb_directory
        WHERE
            directory_id = #{directoryId}
    """)
    fun getOne(@Param("directoryId") directoryId: String): FbDirectory?

    @Insert("""
        INSERT INTO fb_directory (
            directory_id
            , path
            , description
        ) VALUES (
            #{fbDirectory.directoryId},
            #{fbDirectory.path},
            #{fbDirectory.description}
        )
    """)
    fun insertOne(@Param("fbDirectory") fbDirectory: FbDirectory)

    @Select("""
        SELECT
            directory_id AS directoryId
            , path AS path
            , description AS description
        FROM
            fb_directory
        WHERE
            1 = 1
    """)
    fun listDirectory(): List<FbDirectory>

    /**
     * TODO condition 추가
     */
    @Select("""
        SELECT
            d.directory_id AS directoryId
            , d.path AS path
            , d.description AS description
            , v.video_id AS videoId
            , v.video_path AS videoPath
            , v.width AS width
            , v.height AS height
            , v.fps AS fps
            , v.codec AS codec
            , v.avg_bitrate AS avgBitrate
            , v.running_time AS runningTime
            , v.file_size AS fileSize
            , i.image_id AS imageId
            , i.image_path AS imagePath
            , c.casting_id AS castingId
            , c.actor_name AS actorName
            , g.genre_id AS genreId
            , g.genre_name AS genreName
        FROM
            fb_directory d
            LEFT JOIN fb_video v ON d.directory_id = v.directory_id
            LEFT JOIN fb_image i ON d.directory_id = i.directory_id
            LEFT JOIN fb_casting c ON d.directory_id = c.directory_id
            LEFT JOIN fb_genre g ON d.directory_id = g.directory_id
        WHERE
            1 = 1
    """)
    fun listAllJoining(): List<JoinedDirectory>
}
