package com.zh.file.browser.mapper

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper

@Mapper
interface DbInitMapper {

    @Insert("""
        CREATE TABLE IF NOT EXISTS `fb_directory` (
            `directory_id` VARCHAR(200) NOT NULL,
            `path` VARCHAR(255) NOT NULL,
            `description` VARCHAR(20480) NULL,
            PRIMARY KEY (`directory_id`)
        )
    """)
    fun initFbDirectory()

    @Insert("""
        CREATE TABLE IF NOT EXISTS `fb_video` (
            `video_id` INT NOT NULL AUTO_INCREMENT,
            `directory_id` VARCHAR(200) NOT NULL,
            `video_path` VARCHAR(255) NOT NULL,
            `width` INT NOT NULL,
            `height` INT NOT NULL,
            `fps` VARCHAR(45) NOT NULL,
            `codec` VARCHAR(45) NOT NULL,
            `avg_bitrate` VARCHAR(45) NOT NULL,
            `running_time` INT NOT NULL,
            `file_size` INT NOT NULL,
            PRIMARY KEY (`video_id`)
        )
    """)
    fun initFbVideo()

    @Insert("""
        CREATE TABLE IF NOT EXISTS `fb_image` (
            `image_id` INT NOT NULL AUTO_INCREMENT,
            `directory_id` VARCHAR(200) NOT NULL,
            `image_path` VARCHAR(255) NULL,
            PRIMARY KEY (`image_id`)
        );
    """)
    fun initFbImage()

    @Insert("""
        CREATE TABLE IF NOT EXISTS `fb_casting` (
            `casting_id` INT NOT NULL AUTO_INCREMENT,
            `directory_id` VARCHAR(200) NOT NULL,
            `actor_name` VARCHAR(255) NULL,
            PRIMARY KEY (`casting_id`)
        )
    """)
    fun initFbCasting()

    @Insert("""
        CREATE TABLE IF NOT EXISTS `fb_genre` (
            `genre_id` INT NOT NULL AUTO_INCREMENT,
            `directory_id` VARCHAR(200) NOT NULL,
            `genre_name` VARCHAR(255) NULL,
            PRIMARY KEY (`genre_id`)
        )
    """)
    fun initFbGenre()

    @Insert("""
        CREATE TABLE IF NOT EXISTS `fb_thumbnail` (
            `thumbnail_id` INT NOT NULL AUTO_INCREMENT,
            `video_id` INT NOT NULL,
            `thumbnail_path` VARCHAR(255) NOT NULL,
            PRIMARY KEY (`thumbnail_id`)
        )
    """)
    fun initFbThumbnail()
}
