package com.example.hw61.data.datasource

import com.example.hw61.data.model.CounterDto

class EmulateService {

    private var count = 0
    private var isIncrement = true

    fun increment() {
        count++
        isIncrement = true
    }

    fun decrement() {
        count--
        isIncrement = false
    }

    fun getCount(): CounterDto {
        return CounterDto(
            operationType = if (isIncrement) "increment" else "decrement",
            count = count
        )
    }
}