package com.hyunec.happybirthdaytoyou.infra.csv

import com.hyunec.happybirthdaytoyou.domain.member.Member
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import org.springframework.stereotype.Component
import java.io.FileReader
import java.io.Reader
import java.time.MonthDay

@Component
class CsvComponent {
    fun readMembers(filePath: String): HashMap<MonthDay, MutableSet<Member>> {
        val reader: Reader = FileReader(filePath)
        val records: Iterable<CSVRecord> = CSVFormat.RFC4180.parse(reader)
        val members = HashMap<MonthDay, MutableSet<Member>>()

        records.forEach { record ->
            val name = record[0]
            val birthDay = MonthDay.parse(record[1])

            val newMembers: MutableSet<Member> = members[birthDay] ?: mutableSetOf()
            newMembers.add(Member(birthDay, name))

            members[birthDay] = newMembers
        }

        return members
    }
}
