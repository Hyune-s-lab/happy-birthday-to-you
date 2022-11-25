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
    fun readMembers(filePath: String): HashMap<MonthDay, Set<Member>> {
        val reader: Reader = FileReader(filePath)
        val records: Iterable<CSVRecord> = CSVFormat.EXCEL.parse(reader)

        val members = HashMap<MonthDay, Set<Member>>()
        records.forEach { record ->
            val name = record[0]
            val birthDay = MonthDay.parse(record[1])
            members[birthDay] = members[birthDay] ?: (setOf(Member(birthDay, name))
                    union setOf(Member(birthDay, name)))
        }

        return members
    }
}
