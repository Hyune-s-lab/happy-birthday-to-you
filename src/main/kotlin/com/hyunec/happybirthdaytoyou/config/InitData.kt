package com.hyunec.happybirthdaytoyou.config

import com.hyunec.happybirthdaytoyou.domain.member.MemberPool
import com.hyunec.happybirthdaytoyou.infra.csv.CsvComponent
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class InitData(
    private val csvComponent: CsvComponent,
    private val memberPool: MemberPool,
) {

    @PostConstruct
    fun postConstruct() {
        val newMembers = csvComponent.readMembers(ClassLoader.getSystemResource("birthday.csv").file)
        memberPool.reset(newMembers)
    }
}
