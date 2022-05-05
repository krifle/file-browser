package com.zh.file.browser

import com.zh.file.browser.controller.MainController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.awt.EventQueue

@Component
@Profile("release")
class FileBrowserApplicationCommandLineRunner : CommandLineRunner {

    @Autowired
    private lateinit var mainController: MainController

    override fun run(vararg args: String?) {
        EventQueue.invokeLater {
            mainController.isVisible = true
        }
    }
}
