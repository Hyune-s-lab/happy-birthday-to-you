package com.hyunec.happybirthdaytoyou.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfigure {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://hooks.slack.com/services/")
            .build()
    }
}
