package com.zh.file.browser.controller

import com.zh.file.browser.view.MainForm
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Controller
import javax.annotation.PostConstruct
import javax.swing.JFrame

@Controller
@Profile("release")
class MainController : JFrame("File Browser") {

    private var mainForm: MainForm? = null

    @PostConstruct
    fun initialize() {
        this.mainForm = MainForm()
        this.contentPane = mainForm!!.mainPanel
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isVisible = true
        this.pack()
    }
}
