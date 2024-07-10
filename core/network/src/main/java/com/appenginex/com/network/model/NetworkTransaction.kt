package com.appenginex.com.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NetworkTransaction(
    @SerialName("success")
    val success: Boolean
)