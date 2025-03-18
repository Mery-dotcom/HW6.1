package com.example.hw61.data.model

import com.google.gson.annotations.SerializedName

data class CounterDto(
    @SerializedName("operationType")
    val operationType: String,
    val count: Int
)