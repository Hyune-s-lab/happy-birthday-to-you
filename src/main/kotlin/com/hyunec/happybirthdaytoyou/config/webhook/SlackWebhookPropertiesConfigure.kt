package com.hyunec.happybirthdaytoyou.config.webhook

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource

@ConfigurationProperties(prefix = "slack")
@PropertySource(encoding = "UTF-8")
data class SlackWebhookPropertiesConfigure(
    val webhook: List<SlackWebhookProperty>,
) {
    fun findWebhook(type: SlackWebhookPropertyType): SlackWebhookProperty =
        webhook.first { it.type == type }
}
