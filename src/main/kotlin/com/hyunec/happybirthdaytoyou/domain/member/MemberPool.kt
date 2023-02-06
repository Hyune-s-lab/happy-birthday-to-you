package com.hyunec.happybirthdaytoyou.domain.member

import org.springframework.stereotype.Component
import java.time.Month
import java.time.MonthDay

@Component
class MemberPool(private val members: HashMap<MonthDay, Set<Member>> = HashMap()) {
    fun reset(newMembers: Map<MonthDay, Set<Member>>) {
        members.clear()
        members.putAll(newMembers)
    }

    fun find(): Set<Member> {
        return members.values.flatten().toSet()
    }

    fun find(month: Month): Set<Member> {
        return members.filter { month == it.key.month }.values.flatten().toSet()
    }

    fun find(monthDay: MonthDay): Set<Member> {
        return members.filter { monthDay == it.key }.values.flatten().toSet()
    }

    fun save(member: Member) {
        members[member.birthday]?.let {
            members.put(member.birthday, it union setOf(member))
        } ?: members.put(member.birthday, setOf(member))
    }
}
