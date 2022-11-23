package com.hyunec.happybirthdaytoyou.domain.member

import org.springframework.stereotype.Component
import java.time.Month
import java.time.MonthDay

@Component
class MemberPool(private val members: HashMap<MonthDay, List<Member>> = HashMap()) {
    fun reset(newMembers: Map<MonthDay, List<Member>>) {
        members.clear()
        members.putAll(newMembers)
    }

    fun find(): List<Member> {
        return members.values.flatten()
    }

    fun find(month: Month): List<Member> {
        return members.filter { month == it.key.month }.values.flatten()
    }

    fun find(monthDay: MonthDay): List<Member> {
        return members.filter { monthDay == it.key }.values.flatten()
    }
}
