package com.zh.file.browser.mapper

import com.zh.file.browser.model.FbThumbnail
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface FbThumbnailMapper {

    @Select("""
        SELECT
            thumbnail_id AS thumbnailId
            , video_id AS videoId
            , thumbnail_path AS thumbnailPath
        FROM
            fb_thumbnail
        WHERE
            video_id = #{videoId}
    """)
    fun listThumbnailsByVideoId(@Param("videoId") videoId: Int): List<FbThumbnail>

    @Insert("""
        INSERT INTO fb_thumbnail (
            video_id
            , thumbnail_path
        ) VALUES (
            #{fbThumbnail.videoId}
            , #{fbThumbnail.thumbnailPath}
        )
    """)
    fun insertOne(@Param("fbThumbnail") fbThumbnail: FbThumbnail)
}
