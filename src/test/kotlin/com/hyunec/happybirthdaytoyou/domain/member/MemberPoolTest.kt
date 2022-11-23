package com.hyunec.happybirthdaytoyou.domain.member

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Month
import java.time.MonthDay

@SpringBootTest
internal class MemberPoolTest : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    lateinit var memberPool: MemberPool

    init {
        this.Given("InitData 에서 초기화") {
            When("전체 인원 수") {
                val size = memberPool.find().size
                Then("size = 15") {
                    size shouldBe 15
                }
            }

            When("12월 생일자 수") {
                val size = memberPool.find(Month.DECEMBER).size
                Then("size = 3") {
                    size shouldBe 3
                }
            }

            When("9/30 생일자 수") {
                val size = memberPool.find(MonthDay.of(9, 30)).size
                Then("size = 2") {
                    size shouldBe 2
                }
            }
        }
    }
}
