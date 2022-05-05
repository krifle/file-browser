package com.zh.file.browser.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ProjectProperties {
    @Value("\${app.fb.path.root}")
    val rootPath: String? = null

    @Value("\${app.fb.path.player}")
    val playerPath: String? = null

    @Value("\${app.fb.path.ffmpeg}")
    val ffmpegPath: String? = null

    @Value("\${app.fb.path.ffprobe}")
    val ffprobePath: String? = null
}
