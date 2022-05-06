package com.zh.file.browser.view.manager

import com.zh.file.browser.repository.FbDirectoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.swing.Icon
import javax.swing.JList

@Service
class DirectoryListManager {

    @Autowired
    private lateinit var fbDirectoryRepository: FbDirectoryRepository

    fun init(list: JList<Icon>) {
        val fbDirectoryList = fbDirectoryRepository.list()
    }
}
