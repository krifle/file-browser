package com.zh.file.browser.config

import com.zaxxer.hikari.HikariDataSource
import org.h2.tools.Server
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class H2ServerConfiguration {

    companion object {
        private val logger = LoggerFactory.getLogger(H2ServerConfiguration::class.java)
    }

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    fun dataSouce(): DataSource {
        logger.info("Starting H2 Server...")
        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start()
        logger.info("H2 Server started.")
        return HikariDataSource()
    }
}
