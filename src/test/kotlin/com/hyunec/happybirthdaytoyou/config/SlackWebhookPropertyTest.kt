package com.hyunec.happybirthdaytoyou.config

import com.hyunec.happybirthdaytoyou.config.webhook.SlackWebhookProperty
import com.hyunec.happybirthdaytoyou.config.webhook.SlackWebhookPropertyType
import com.hyunec.happybirthdaytoyou.domain.member.Member
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain
import java.time.MonthDay

class SlackWebhookPropertyTest : BehaviorSpec({
    Given("webhook 출력을 위한 property 준비") {
        val type = SlackWebhookPropertyType.FIRST_DAY_OF_MONTH
        val key = "tempKey"
        val headerTemplate = ">:love_letter:이달의 생일 미리 알림이 도착했어요:love_letter:"
        val bodyTemplate = ">                    :gift: *\$name* 님 (\$birthday)"
        val footerTemplate =
            ">:love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter:"

        val property = SlackWebhookProperty(type, key, headerTemplate, bodyTemplate, footerTemplate)
        val members = setOf(
            Member(MonthDay.of(12, 12), "홍길동"),
            Member(MonthDay.of(6, 12), "아무개"),
        )
        val replaceStringsForBody = type.replaceStringsForBody(members)

        When("webhook 을 통해 문자열 생성") {
            val printString = property.printString(replaceStringsForBody)

            Then("response is ok") {
                members.forEach {
                    printString shouldContain it.name
                    printString shouldContain "${it.birthday.monthValue}-${it.birthday.dayOfMonth}"
                }
                println(printString)
            }
        }
    }
})
