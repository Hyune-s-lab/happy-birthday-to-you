package com.hyunec.happybirthdaytoyou.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.PropertySource

@ConstructorBinding
@ConfigurationProperties(prefix = "slack")
@PropertySource(encoding = "UTF-8")
data class SlackWebhookPropertiesConfigure(
    val webhook: List<SlackWebhookProperty>,
) {
    fun findWebhook(name: String): SlackWebhookProperty {
        return webhook.first { it.name == name }
    }
}
