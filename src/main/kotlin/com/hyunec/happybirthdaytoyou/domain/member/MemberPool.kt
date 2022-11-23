package com.hyunec.happybirthdaytoyou.domain.member

import org.springframework.stereotype.Component
import java.time.Month
import java.time.MonthDay

@Component
class MemberPool(private val members: HashMap<MonthDay, List<Member>> = HashMap()) {
    fun reset(newNembers: Map<MonthDay, List<Member>>) {
        members.clear()
        members.putAll(newNembers)
    }

    fun find(): List<Member> {
        return members.values.flatten()
    }

    fun find(month: Month): List<Member> {
        return members.filter { MonthDay.now().month == it.key.month }.values.flatten()
    }

    fun find(monthDay: MonthDay): List<Member> {
        return members.filter { MonthDay.now() == it.key }.values.flatten()
    }
}
