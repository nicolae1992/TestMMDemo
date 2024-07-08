package com.appenginex.com.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkDeleteCard(
    @SerialName("code")
    val code: Int,
    @SerialName("lang")
    val lang: String,
    @SerialName("response")
    val response: String,
    @SerialName("status")
    val status: Boolean
)