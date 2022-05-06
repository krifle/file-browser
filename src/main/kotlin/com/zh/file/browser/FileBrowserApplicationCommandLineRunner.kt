package com.zh.file.browser

import com.zh.file.browser.controller.MainFormController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.awt.EventQueue

@Profile("!dao")
@Component
class FileBrowserApplicationCommandLineRunner : CommandLineRunner {

    @Autowired
    private lateinit var mainFormController: MainFormController

    override fun run(vararg args: String?) {
        EventQueue.invokeLater {
            mainFormController.isVisible = true
        }
    }
}
