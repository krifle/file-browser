package com.zh.file.browser.view.manager

import com.zh.file.browser.mapper.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.swing.Icon
import javax.swing.JList

@Service
class DirectoryListManager {

    @Autowired
    private lateinit var fbDirectoryMapper: FbDirectoryMapper
    @Autowired
    private lateinit var fbImageMapper: FbImageMapper
    @Autowired
    private lateinit var fbVideoMapper: FbVideoMapper
    @Autowired
    private lateinit var fbCastingMapper: FbCastingMapper
    @Autowired
    private lateinit var fbGenreMapper: FbGenreMapper

    fun init(list: JList<Icon>) {
        val fbDirectoryList = fbDirectoryMapper.listDirectory()
        // TODO 성능 향상 필요
        fbDirectoryList.forEach {
            it.images = fbImageMapper.listImageByDirectoryId(it.directoryId!!)
            it.videos = fbVideoMapper.listVideoByDirectoryId(it.directoryId!!)
            it.castings = fbCastingMapper.listCastingByDirectoryId(it.directoryId!!)
            it.genres = fbGenreMapper.listGenreByDirectoryId(it.directoryId!!)
        }

    }
}
