package com.hyunec.happybirthdaytoyou.config

import com.hyunec.happybirthdaytoyou.config.webhook.SlackWebhookProperty
import com.hyunec.happybirthdaytoyou.config.webhook.SlackWebhookPropertyType
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain

class SlackWebhookPropertyTest : BehaviorSpec({
    Given("출력을 위한 webhookProperty 준비") {
        val name = SlackWebhookPropertyType.FIRST_DAY_OF_MONTH
        val key = "tempKey"
        val header = ">:love_letter:이달의 생일 미리 알림이 도착했어요:love_letter:"
        val body = ">                    :gift: *\$name* 님 (\$birthday)"
        val footer =
            ">:love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter::love_letter:"

        val property = SlackWebhookProperty(name, key, header, body, footer)

        val expectedName = "홍길동"
        val expectedBirthday = "12-12"

        val replaceStrings = listOf(
            mapOf(
                "\$name" to expectedName, "\$birthday" to expectedBirthday
            )
        )

        When("webhookProperty 를 통해 문자열 생성") {
            val printString = property.printString(replaceStrings)

            Then("response is ok") {
                printString shouldContain expectedName
                printString shouldContain expectedBirthday

                println(printString)
            }
        }
    }
})
