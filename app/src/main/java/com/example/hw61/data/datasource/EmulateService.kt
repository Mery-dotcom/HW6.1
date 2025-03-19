package com.example.hw61.data.datasource

import com.example.hw61.data.model.CounterDto

class EmulateService {

    private var count = 0
    private var isIncrement = true

    suspend fun increment() {
        count++
        isIncrement = true
    }

    suspend fun decrement() {
        count--
        isIncrement = false
    }

   suspend fun getCount(): CounterDto {
        return CounterDto(
            operationType = if (isIncrement) "increment" else "decrement",
            count = count
        )
    }
}