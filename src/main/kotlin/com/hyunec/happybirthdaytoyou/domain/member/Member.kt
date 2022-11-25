package com.hyunec.happybirthdaytoyou.domain.member

import java.time.MonthDay

data class Member(
    val birthday: MonthDay,
    val name: String,
) {
    fun csvFormmating(): String {
        return "$name,$birthday"
    }
}
