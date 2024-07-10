package com.appenginex.com.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAddCreditCard(
    @SerialName("id")
    val id: Long,
    @SerialName("status")
    val status: Boolean,
    @SerialName("type")
    val type: String?,
    @SerialName("cardNumber")
    val cardNumber: String,
)