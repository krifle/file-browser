package com.zh.file.browser.config

import com.zh.file.browser.config.service.FileBrowser
import com.zh.file.browser.config.service.LeafDirectoryAnalyzer
import com.zh.file.browser.config.service.LeafDirectoryManager
import com.zh.file.browser.mapper.FbDirectoryMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.io.File
import javax.annotation.PostConstruct

@Profile("!dao")
@Configuration
class FileInitializerConfig {

    @Autowired
    private lateinit var projectProperties: ProjectProperties
    @Autowired
    private lateinit var fbDirectoryMapper: FbDirectoryMapper
    @Autowired
    private lateinit var leafDirectoryManager: LeafDirectoryManager

    @PostConstruct
    fun init() {
        val rootDirectory = File(projectProperties.rootPath!!)
        FileBrowser(rootDirectory).getDirectoryList()
            .map { file ->
                LeafDirectoryAnalyzer(file).analysis()
            }
            .forEach { leafDirectory ->
                val fbDirectory = fbDirectoryMapper.getOne(leafDirectory.getId())
                if (fbDirectory != null) {
                    leafDirectoryManager.update(leafDirectory, fbDirectory)
                } else {
                    leafDirectoryManager.insert(leafDirectory)
                }
            }
    }
}
