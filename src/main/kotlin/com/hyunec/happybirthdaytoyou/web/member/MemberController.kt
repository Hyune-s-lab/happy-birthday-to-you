package com.hyunec.happybirthdaytoyou.web.member

import com.hyunec.happybirthdaytoyou.domain.member.Member
import com.hyunec.happybirthdaytoyou.domain.member.MemberPool
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

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
}
