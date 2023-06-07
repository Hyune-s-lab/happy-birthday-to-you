package com.hyunec.happybirthdaytoyou.config

import com.hyunec.happybirthdaytoyou.config.webhook.SlackWebhookPropertiesConfigure
import com.hyunec.happybirthdaytoyou.config.webhook.SlackWebhookPropertyType
import com.hyunec.happybirthdaytoyou.domain.member.MemberPool
import com.hyunec.happybirthdaytoyou.infra.slack.IncomingWebhookSender
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.MonthDay

@Component
class SchedulerConfigure(
    private val slackWebhookPropertiesConfigure: SlackWebhookPropertiesConfigure,
    private val incomingWebhookSender: IncomingWebhookSender,
    private val memberPool: MemberPool,
) {
    /**
     * 매 월 첫째날 9시
     */
    @Scheduled(cron = "0 0 9 1 * *")
//    @Scheduled(cron = "0/5 * * * * *") // 5초 단위로 실행
    fun everyFirstDayOfMonth() {
        val thisMonth = MonthDay.now().month
        val members = memberPool.find(thisMonth)
        val webhookType = SlackWebhookPropertyType.FIRST_DAY_OF_MONTH
        val webhookProperty = slackWebhookPropertiesConfigure.findWebhook(webhookType)
        val replaceStringsForBody = webhookType.replaceStringsForBody(members)

        if (replaceStringsForBody.isNotEmpty()) {
            incomingWebhookSender.send(webhookProperty.key, webhookProperty.printString(replaceStringsForBody))
                .doOnSuccess { log.info("### success everyFirstDayOfMonth: $thisMonth=$replaceStringsForBody") }
                .doOnError { log.error("### failed everyFirstDayOfMonth: $it") }
                .subscribe()
        }

        log.info("### $thisMonth=$replaceStringsForBody")
    }

    /**
     * 매일 9시
     */
    @Scheduled(cron = "0 0 9 * * *")
//    @Scheduled(cron = "0/5 * * * * *") // 5초 단위로 실행
    fun everyDay() {
        val today = MonthDay.now()
        val members = memberPool.find(today)
        val webhookType = SlackWebhookPropertyType.TODAY
        val webhookProperty = slackWebhookPropertiesConfigure.findWebhook(webhookType)
        val replaceStringsForBody = webhookType.replaceStringsForBody(members)

        if (replaceStringsForBody.isNotEmpty()) {
            incomingWebhookSender.send(webhookProperty.key, webhookProperty.printString(replaceStringsForBody))
                .doOnSuccess { log.info("### success everyDay: $today=$replaceStringsForBody") }
                .doOnError { log.error("### failed everyDay: $it") }
                .subscribe()
        }

        log.info("### $today=$replaceStringsForBody")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
