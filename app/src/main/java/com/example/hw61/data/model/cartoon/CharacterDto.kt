package com.example.hw61.data.model.cartoon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("status")
    var status: String? = null,
    @SerialName("species")
    var species: String? = null,
    @SerialName("type")
    var type: String? = null,
    @SerialName("gender")
    var gender: String? = null,
    @SerialName("origin")
    var origin: OriginDto? = null,
    @SerialName("location")
    var location: LocationDto? = null,
    @SerialName("image")
    var image: String? = null,
    @SerialName("episode")
    var episode: List<String?>? = null,
    @SerialName("url")
    var url: String? = null,
    @SerialName("created")
    var created: String? = null
)