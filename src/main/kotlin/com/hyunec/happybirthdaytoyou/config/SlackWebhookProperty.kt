package com.hyunec.happybirthdaytoyou.config

data class SlackWebhookProperty(
    var name: String,
    var key: String,
    var format: String,
) {
    fun printString(delimiters: Map<String, String>): String {
        var printString = format
        delimiters.keys.forEach { printString = printString.replace(it, delimiters[it]!!) }
        return printString
    }
}
