package com.hyunec.happybirthdaytoyou.config

import com.hyunec.happybirthdaytoyou.domain.member.Member
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import org.springframework.stereotype.Component
import java.io.FileReader
import java.io.Reader
import java.time.MonthDay
import javax.annotation.PostConstruct

@Component
class InitData {

    @PostConstruct
    fun postConstruct() {
        val reader: Reader = FileReader(ClassLoader.getSystemResource("birthday.csv").file)
        val records: Iterable<CSVRecord> = CSVFormat.RFC4180.parse(reader)
        for (record in records) {
            val member = Member(record[0], MonthDay.parse(record[1]))
            println("### $member, ${member.birthday.month}")
        }
    }
}
