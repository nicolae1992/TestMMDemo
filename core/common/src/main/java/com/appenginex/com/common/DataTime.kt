package com.appenginex.com.common

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getCurrentTime(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    return current.format(formatter)
}