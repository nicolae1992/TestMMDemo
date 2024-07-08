package com.appenginex.com.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAddCreditCard(
    @SerialName("code")
    val code: Int,
    @SerialName("lang")
    val lang: String,
    @SerialName("status")
    val status: Boolean,
    @SerialName("url")
    val url: String?
)