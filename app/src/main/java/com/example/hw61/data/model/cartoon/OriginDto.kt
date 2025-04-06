package com.example.hw61.data.model.cartoon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OriginDto(
    @SerialName("name")
    var name: String? = null,
    @SerialName("url")
    var url: String? = null
)