package com.zh.file.browser.controller

import com.zh.file.browser.config.ProjectProperties
import com.zh.file.browser.view.MainForm2
import com.zh.file.browser.view.manager.ActorSelectManager
import com.zh.file.browser.view.manager.DirectoryListManager
import com.zh.file.browser.view.manager.GenreSelectManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Controller
import javax.annotation.PostConstruct
import javax.swing.JFrame

@Profile("!dao")
@Controller
class MainFormController : JFrame("File Browser") {

    private var mainForm2: MainForm2? = null

    @Autowired
    private lateinit var projectProperties: ProjectProperties
    @Autowired
    private lateinit var actorSelectManager: ActorSelectManager
    @Autowired
    private lateinit var genreSelectManager: GenreSelectManager
    @Autowired
    private lateinit var directoryListManager: DirectoryListManager

    @PostConstruct
    fun initialize() {
        this.mainForm2 = MainForm2()
        this.contentPane = mainForm2!!.mainPanel
        this.contentPane.setSize(800, 800)
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isVisible = true
        this.pack()

        initPathPanel()
        actorSelectManager.init(mainForm2!!.actorSelectList)
        genreSelectManager.init(mainForm2!!.genreSelectList)
        directoryListManager.init(mainForm2!!.directoryList, mainForm2!!.directoryScroll)

        initStatusLabel()
    }

    private fun initPathPanel() {
        mainForm2!!.rootPathLabel.text = projectProperties.rootPath!!
    }

    private fun initStatusLabel() {
        mainForm2!!.statusLabel.text = "Loading completed..."
    }
}
