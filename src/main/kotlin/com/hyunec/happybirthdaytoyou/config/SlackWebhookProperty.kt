package com.hyunec.happybirthdaytoyou.config

data class SlackWebhookProperty(
    var name: String,
    var key: String,
    var header: String,
    var body: String,
    var footer: String,
) {
    fun printString(delimiters: List<Map<String, String>>): String {
        return "$header\n" +
            delimiters.fold("") { result, maps -> "$result${generateBody(maps)}\n" } +
            footer
    }

    private fun generateBody(delimiters: Map<String, String>): String {
        return delimiters.keys
            .fold(body) { result, key -> result.replace(key, delimiters[key]!!) }
    }
}
