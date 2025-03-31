package com.example.hw61.data.mapper

import com.example.hw61.data.model.CounterDto
import com.example.hw61.data.model.ExampleDto
import com.example.hw61.domain.model.CounterEntity
import com.example.hw61.domain.model.Example
import com.example.hw61.domain.model.OperationType

fun CounterDto.toDomain(): CounterEntity {
    return CounterEntity(
        count = this.count,
        operationType = OperationType.fromString(this.operationType)
    )
}

fun ExampleDto.toDomain() = Example(
    id = id,
    value = value,
    conversion_rates = conversion_rates
)