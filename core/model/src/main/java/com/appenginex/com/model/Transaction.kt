package com.appenginex.com.model

data class Transaction(
    val time: String = "00:00",
    val amount: String = "0",
    val recipientName: String = "Unknown",
    val cardNumber: String = "Unknown"
)