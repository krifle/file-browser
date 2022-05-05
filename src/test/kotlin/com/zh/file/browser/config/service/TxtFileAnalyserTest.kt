package com.zh.file.browser.config.service

import com.google.gson.Gson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

internal class TxtFileAnalyserTest {

    @Test
    fun `analysis txt file as given specification`() {
        // given
        val txtResource = javaClass.getResource("/sample/0BwRXrQIUBo/0BwRXrQIUBo.txt") ?: throw IllegalStateException("")
        val txtFile = File(txtResource.file)

        // when
        val result = TxtFileAnalyser(txtFile).analysis()

        // then
        println(Gson().toJson(result))
        assertThat(result.id).isEqualTo("0BwRXrQIUBo")
        assertThat(result.actorList).containsExactlyInAnyOrder("호쇼우 마린", "루시아")
        assertThat(result.genreList).containsExactlyInAnyOrder("뮤비", "3분")
        assertThat(result.description).isEqualTo("""
            가사가사가사가
            가사가사가사가사가사
            노랫말 노랫말
        """.trimIndent())
    }
}
