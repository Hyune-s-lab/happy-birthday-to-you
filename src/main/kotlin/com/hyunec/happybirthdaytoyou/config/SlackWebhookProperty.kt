package com.hyunec.happybirthdaytoyou.config

data class SlackWebhookProperty(
    val name: String,
    val key: String,
    val header: String,
    val body: String,
    val footer: String,
) {
    fun printString(delimiters: List<Map<String, String>>): String =
        "$header\n" + delimiters.fold("") { result, maps -> "$result${generateBody(maps)}\n" } + footer

    private fun generateBody(delimiters: Map<String, String>): String =
        delimiters.keys.fold(body) { result, key -> result.replace(key, delimiters[key]!!) }
}
