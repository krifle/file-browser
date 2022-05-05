package com.zh.file.browser.mapper

import com.zh.file.browser.model.FbVideo
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface FbVideoMapper {

    @Select("""
        SELECT
            video_id AS videoId
            , directory_id AS directoryId
            , video_path AS videoPath
            , width AS width
            , height AS height
            , fps AS fps
            , codec AS codec
            , avg_bitrate AS avgBitrate
            , running_time AS runningTime
            , file_size AS fileSize
        FROM
            fb_video
        WHERE
            directory_id = #{directoryId}
    """)
    fun listVideoByDirectoryId(@Param("directoryId") directoryId: String): List<FbVideo>

    @Insert("""
        INSERT INTO fb_video (
            directory_id
            , video_path
            , width
            , height
            , fps
            , codec
            , avg_bitrate
            , running_time
            , file_size
        ) VALUES (
            #{fbVideo.directoryId}
            , #{fbVideo.videoPath}
            , #{fbVideo.width}
            , #{fbVideo.height}
            , #{fbVideo.fps}
            , #{fbVideo.codec}
            , #{fbVideo.avgBitrate}
            , #{fbVideo.runningTime}
            , #{fbVideo.fileSize}
        )
    """)
    fun insertOne(@Param("fbVideo") fbVideo: FbVideo)
}
