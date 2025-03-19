package com.example.hw61.data.mapper

import com.example.hw61.data.model.CounterDto
import com.example.hw61.domain.model.CounterEntity
import com.example.hw61.domain.model.OperationType

suspend fun CounterDto.toDomain(): CounterEntity {
    return CounterEntity(
        count = this.count,
        operationType = OperationType.fromString(this.operationType)
    )
}