package com.hyunec.happybirthdaytoyou.config

import com.hyunec.happybirthdaytoyou.domain.member.MemberPool
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.MonthDay

@Component
class SchedulerConfigure(private val memberPool: MemberPool) {

    /**
     * 매 월 첫째날 9시
     */
    @Scheduled(cron = "0 0 9 1 * *")
    fun everyFirstDayOfMonth() {
        val thisMonth = MonthDay.now().month
        val members = memberPool.find(thisMonth)
        log.info("### $thisMonth=$members")
    }

    /**
     * 매일 9시
     */
    @Scheduled(cron = "0 0 9 * * *")
    fun everyDay() {
        val today = MonthDay.now()
        val members = memberPool.find(today)
        log.info("### $today=$members")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
