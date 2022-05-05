package com.zh.file.browser

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@Rollback
@ActiveProfiles(profiles = ["dao"])
interface AbstractDbTest
