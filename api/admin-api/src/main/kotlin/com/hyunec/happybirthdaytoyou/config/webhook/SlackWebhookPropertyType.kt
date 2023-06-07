package com.hyunec.happybirthdaytoyou.config.webhook

import com.hyunec.happybirthdaytoyou.domain.member.Member

enum class SlackWebhookPropertyType {
    FIRST_DAY_OF_MONTH {
        override fun replaceStringsForBody(members: Collection<Member>): List<Map<String, String>> {
            return members.map {
                mapOf(
                    "\$name" to it.name,
                    "\$birthday" to "${it.birthday.monthValue}-${it.birthday.dayOfMonth}"
                )
            }
        }
    },
    TODAY {
        override fun replaceStringsForBody(members: Collection<Member>): List<Map<String, String>> {
            return members.map { mapOf("\$name" to it.name) }
        }
    },
    ;

    abstract fun replaceStringsForBody(members: Collection<Member>): List<Map<String, String>>
}
