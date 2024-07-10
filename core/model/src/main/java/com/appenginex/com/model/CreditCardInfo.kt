package com.appenginex.com.model

data class CreditCardInfo(
    val cardNumber: String,
    val customerName: String,
    val cvv: String,
    val expiry: String,
    val cardType: String
)