package com.hyunec.happybirthdaytoyou.config

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
//    @Scheduled(cron = "* * * * * *") // 1초 단위로 실행
    fun everyFirstDayOfMonth() {
        val thisMonth = MonthDay.now().month
        val members = memberPool.find(thisMonth)
        val slackWebhook = slackWebhookPropertiesConfigure.findWebhook("test")
        members.forEach { m ->
            val printString = slackWebhook.printString(mapOf("\$name" to m.name))
            incomingWebhookSender.send(slackWebhook.key, printString)
                .doOnSuccess { log.info("### $thisMonth=${m.name}") }
                .doOnError { ex -> log.error("### ${ex.stackTrace}") }
                .subscribe()
        }
    }

    /**
     * 매일 9시
     */
    @Scheduled(cron = "0 0 9 * * *")
    fun everyDay() {
        val today = MonthDay.now()
        val members = memberPool.find(today)
        val slackWebhook = slackWebhookPropertiesConfigure.findWebhook("test")
        members.forEach { m ->
            val printString = slackWebhook.printString(mapOf("\$name" to m.name))
            incomingWebhookSender.send(slackWebhook.key, printString)
                .doOnSuccess { log.info("### $today=${m.name}") }
                .doOnError { ex -> log.error("### ${ex.stackTrace}") }
                .subscribe()
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
