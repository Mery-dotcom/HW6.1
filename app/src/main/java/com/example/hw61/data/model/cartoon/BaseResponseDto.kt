package com.example.hw61.data.model.cartoon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseDto(
    @SerialName("info")
    var info: InfoDto? = null,
    @SerialName("results")
    var results: List<CharacterDto>? = null
)