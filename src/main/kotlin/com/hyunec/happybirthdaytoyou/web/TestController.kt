package com.hyunec.happybirthdaytoyou.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class TestController {

    @GetMapping("/test/log")
    fun log() {
        log.info("### ${LocalDateTime.now()}")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
