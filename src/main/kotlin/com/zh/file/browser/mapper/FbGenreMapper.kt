package com.zh.file.browser.mapper

import com.zh.file.browser.model.FbGenre
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface FbGenreMapper {

    @Select("""
        SELECT
            genre_id AS genreId
            , directory_id AS directoryId
            , genre_name AS genreName
        FROM
            fb_genre
        WHERE
            directory_id = #{directoryId}
    """)
    fun listGenreByDirectoryId(@Param("directoryId") directoryId: String): List<FbGenre>

    @Insert("""
        INSERT INTO fb_genre (
            directory_id
            , genre_name
        ) VALUES (
            #{fbGenre.directoryId}
            , #{fbGenre.genreName}
        )
    """)
    fun insertOne(@Param("fbGenre") fbGenre: FbGenre)
}
