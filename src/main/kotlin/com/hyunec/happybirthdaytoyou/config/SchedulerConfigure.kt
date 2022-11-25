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
//    @Scheduled(cron = "0/5 * * * * *") // 5초 단위로 실행
    fun everyFirstDayOfMonth() {
        val thisMonth = MonthDay.now().month
        val members = memberPool.find(thisMonth)
        val slackWebhook = slackWebhookPropertiesConfigure.findWebhook("FIRST_DAY_OF_MONTH")
        val names = members.map {
            mapOf(
                "\$name" to it.name,
                "\$birthday" to "${it.birthday.monthValue}-${it.birthday.dayOfMonth}"
            )
        }

        if (names.isNotEmpty()) {
            incomingWebhookSender.send(slackWebhook.key, slackWebhook.printString(names))
                .doOnSuccess { log.info("### success $thisMonth=$names") }
                .doOnError { ex -> log.error("### ${ex.stackTrace}") }
                .subscribe()
        }

        log.info("### $thisMonth=$names")
    }

    /**
     * 매일 9시
     */
    @Scheduled(cron = "0 0 9 * * *")
//    @Scheduled(cron = "0/5 * * * * *") // 5초 단위로 실행
    fun everyDay() {
        val today = MonthDay.now()
        val members = memberPool.find(today)
        val slackWebhook = slackWebhookPropertiesConfigure.findWebhook("TODAY")
        val names = members.map { mapOf("\$name" to it.name) }

        if (names.isNotEmpty()) {
            incomingWebhookSender.send(slackWebhook.key, slackWebhook.printString(names))
                .doOnSuccess { log.info("### success $today=$names") }
                .doOnError { ex -> log.error("### ${ex.stackTrace}") }
                .subscribe()
        }

        log.info("### $today=$names")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
