package com.example.hw61.domain.model

enum class OperationType(
    val value: String
){
    INCREMENT("increment"),
    DECREMENT("decrement"),
    UNKNOWN("unknown");

    companion object {
       suspend fun fromString(value: String): OperationType {
            return OperationType.entries.find { value == it.value }?: UNKNOWN
        }
    }
}