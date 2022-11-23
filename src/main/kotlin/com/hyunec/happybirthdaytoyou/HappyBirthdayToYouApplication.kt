package com.hyunec.happybirthdaytoyou

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class HappyBirthdayToYouApplication

fun main(args: Array<String>) {
    runApplication<HappyBirthdayToYouApplication>(*args)
}
