package com.example.hw61.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExampleDto(
    @SerialName("id")
    val id: Int,
    @SerialName("value")
    val value: String,
    @SerialName("conversion_rates")
    val conversion_rates: String
)