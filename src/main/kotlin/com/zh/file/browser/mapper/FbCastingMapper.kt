package com.zh.file.browser.mapper

import com.zh.file.browser.model.FbCasting
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface FbCastingMapper {

    @Select("""
        SELECT
            casting_id AS castingId
            , directory_id AS directoryId
            , actor_name AS actorName
        FROM
            fb_casting
        WHERE
            directory_id = #{directoryId}
    """)
    fun listCastingByDirectoryId(@Param("directoryId") directoryId: String): List<FbCasting>

    @Insert("""
        INSERT INTO fb_casting (
            directory_id
            , actor_name
        ) VALUES (
            #{fbCasting.directoryId}
            , #{fbCasting.actorName}
        )
    """)
    fun insertOne(@Param("fbCasting") fbCasting: FbCasting)

    @Select("""
        SELECT DISTINCT
            actor_name
        FROM
            fb_casting
    """)
    fun listAllDistinctActors(): List<String>
}
