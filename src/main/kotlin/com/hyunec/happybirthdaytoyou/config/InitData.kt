package com.hyunec.happybirthdaytoyou.config

import com.hyunec.happybirthdaytoyou.domain.member.Member
import com.hyunec.happybirthdaytoyou.domain.member.MemberPool
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import org.springframework.context.annotation.Configuration
import java.io.FileReader
import java.io.Reader
import java.time.MonthDay
import javax.annotation.PostConstruct

@Configuration
class InitData(private val memberPool: MemberPool) {

    @PostConstruct
    fun postConstruct() {
        val reader: Reader = FileReader(ClassLoader.getSystemResource("birthday.csv").file)
        val records: Iterable<CSVRecord> = CSVFormat.RFC4180.parse(reader)
        val newMembers = HashMap<MonthDay, MutableList<Member>>()

        records.forEach { record ->
            val name = record[0]
            val birthDay = MonthDay.parse(record[1])

            val members: MutableList<Member> = newMembers[birthDay] ?: mutableListOf()
            members.add(Member(birthDay, name))

            newMembers[birthDay] = members
        }

        memberPool.reset(newMembers)
    }
}
