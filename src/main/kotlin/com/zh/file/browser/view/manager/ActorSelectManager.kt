package com.zh.file.browser.view.manager

import com.zh.file.browser.mapper.FbCastingMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.swing.DefaultListModel
import javax.swing.JList
import javax.swing.ListSelectionModel

@Service
class ActorSelectManager {

    @Autowired
    private lateinit var fbCastingMapper: FbCastingMapper

    fun init(actorSelectList: JList<String>) {
        val actors = fbCastingMapper.listAllDistinctActors()
        val actorListModel = DefaultListModel<String>()
        actors.forEach {
            actorListModel.addElement(it)
        }

        actorSelectList.selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
        actorSelectList.model = actorListModel
    }
}
