package com.example.hw61.data.model.cartoon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    @SerialName("count")
    var count: Int? = null,
    @SerialName("pages")
    var pages: Int? = null,
    @SerialName("next")
    var next: String? = null,
    @SerialName("prev")
    var prev: String? = null
)