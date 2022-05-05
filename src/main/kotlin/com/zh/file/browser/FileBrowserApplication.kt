package com.zh.file.browser

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class FileBrowserApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(FileBrowserApplication::class.java)
        .headless(false)
        .web(WebApplicationType.NONE)
        .run("")
}
