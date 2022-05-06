package com.zh.file.browser.view.manager

import com.zh.file.browser.repository.FbDirectoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.awt.Image
import java.io.File
import javax.imageio.ImageIO
import javax.swing.DefaultListModel
import javax.swing.ImageIcon
import javax.swing.JList
import javax.swing.JScrollPane

@Service
class DirectoryListManager {

    @Autowired
    private lateinit var fbDirectoryRepository: FbDirectoryRepository

    fun init(list: JList<ImageIcon>, directoryScroll: JScrollPane) {
        val fbDirectoryList = fbDirectoryRepository.list()
        val repImgList = fbDirectoryList.map { it.images?.first() }
        val listModel = DefaultListModel<ImageIcon>()
        repImgList.forEachIndexed { index, img ->
            val imageFile = File(img!!.imagePath!!)
            val imageIcon = ImageIcon(ImageIO.read(imageFile).getScaledInstance(120, 120, Image.SCALE_FAST))
            listModel.add(index, imageIcon)
        }
        list.visibleRowCount = 1
        list.model = listModel
        directoryScroll.setViewportView(list)
    }
}
