package com.hyunec.happybirthdaytoyou.config

import com.hyunec.happybirthdaytoyou.domain.member.MemberPool
import com.hyunec.happybirthdaytoyou.infra.csv.CsvComponent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class InitData(
    private val csvComponent: CsvComponent,
    private val memberPool: MemberPool,
) {

    @PostConstruct
    fun postConstruct() {
        val newMembers = csvComponent.readMembers("birthday.csv")
        memberPool.reset(newMembers)

        log.info("### initData success. memberSize=${memberPool.find().size}")
        log.info("### initData success. member=${memberPool.find()}")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
