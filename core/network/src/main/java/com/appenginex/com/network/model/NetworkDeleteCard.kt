package com.appenginex.com.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkDeleteCard(
    @SerialName("id")
    val id: Long,
    @SerialName("type")
    val type: String,
    @SerialName("status")
    val status: Boolean
)