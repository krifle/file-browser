package com.zh.file.browser.view.manager

import com.zh.file.browser.mapper.FbGenreMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.swing.DefaultListModel
import javax.swing.JList
import javax.swing.ListSelectionModel

@Service
class GenreSelectManager {

    @Autowired
    private lateinit var fbGenreMapper: FbGenreMapper

    fun init(genreSelectList: JList<String>) {
        val genres = fbGenreMapper.listAllDistinctGenre()
        val genreListModel = DefaultListModel<String>()
        genres.forEach {
            genreListModel.addElement(it)
        }

        genreSelectList.selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
        genreSelectList.model = genreListModel
    }
}
