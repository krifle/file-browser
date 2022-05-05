package com.zh.file.browser.mapper

import com.zh.file.browser.model.FbImage
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface FbImageMapper {

    @Select("""
        SELECT
            image_id AS imageId
            , directory_id AS directoryId
            , image_path AS imagePath
        FROM
            fb_image
        WHERE
            directory_id = #{directoryId}
    """)
    fun listImageByDirectoryId(@Param("directoryId") directoryId: String): List<FbImage>

    @Insert("""
        INSERT INTO fb_image (
            directory_id
            , image_path
        ) VALUES (
            #{fbImage.directoryId}
            , #{fbImage.imagePath}
        )
    """)
    fun insertOne(@Param("fbImage") fbImage: FbImage)
}
