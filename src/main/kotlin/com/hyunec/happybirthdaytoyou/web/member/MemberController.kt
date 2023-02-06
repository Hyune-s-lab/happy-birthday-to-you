package com.hyunec.happybirthdaytoyou.web.member

import com.hyunec.happybirthdaytoyou.domain.member.Member
import com.hyunec.happybirthdaytoyou.domain.member.MemberPool
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.MonthDay

@RestController
class MemberController(private val memberPool: MemberPool) {
    @GetMapping("/members")
    fun find(): Set<Member> {
        return memberPool.find()
    }

    @GetMapping("/members/csv")
    fun getCsv(): String {
        return memberPool.find()
            .joinToString(separator = "\n") { it.csvFormmating() }
    }

    @PostMapping("/members/bulk")
    fun postBulk(@RequestBody requestString: String) {
        requestString.split("\n")
            .map {
                val split = it.split(",")
                Member(MonthDay.parse(split[1]), split[0])
            }
            .forEach { memberPool.save(it) }
    }

    @DeleteMapping("/members/reset")
    fun reset() {
        memberPool.reset(HashMap())
    }
}
