package com.hyunec.happybirthdaytoyou.config.webhook

data class SlackWebhookProperty(
    val type: SlackWebhookPropertyType,
    val key: String,
    val headerTemplate: String,
    val bodyTemplate: String,
    val footerTemplate: String,
) {
    fun printString(replaceStringsForBody: List<Map<String, String>>): String {
        val bodyStrings = replaceStringsForBody.fold("") { result, maps ->
            "$result${bodyString(maps)}\n"
        }
        return "$headerTemplate\n" + bodyStrings + footerTemplate
    }

    private fun bodyString(replaceStrings: Map<String, String>): String =
        replaceStrings.keys.fold(bodyTemplate) { result, key ->
            result.replace(key, replaceStrings[key]!!)
        }
}
