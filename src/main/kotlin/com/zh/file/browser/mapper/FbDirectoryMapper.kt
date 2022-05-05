package com.zh.file.browser.mapper

import com.zh.file.browser.model.FbDirectory
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
}
