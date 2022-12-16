package com.hyunec.happybirthdaytoyou.infra.slack

import com.hyunec.happybirthdaytoyou.config.WebClientConfigure
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest

@Ignored // 테스트시 Ignored 제거
@SpringBootTest(classes = [WebClientConfigure::class, IncomingWebhookSender::class])
internal class IncomingWebhookSenderRealTest(val sender: IncomingWebhookSender) : BehaviorSpec({
    Given("set test data") {
        // webhookKey 를 넣어야 합니다.
        val webhookKey = "xxxxxxxx/xxxxxxxx/xxxxxxxxxxxxxxxx"
        val body = mapOf("text" to "Hello, World!")

        When("request by webhookSender") {
            val response = withContext(Dispatchers.IO) {
                sender.send(webhookKey, body).block()
            }

            Then("response is ok") {
                response shouldBe "ok"
            }
        }
    }
})
