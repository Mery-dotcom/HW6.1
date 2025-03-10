package com.example.hw61.data.repository

import com.example.hw61.data.datasource.EmulateService
import com.example.hw61.domain.model.CounterEntity
import com.example.hw61.domain.model.OperationType
import com.example.hw61.domain.repository.CounterRepository

class CounterRepositoryImpl : CounterRepository {

    private val api = EmulateService()

    override fun increment() {
        api.increment()
    }

    override fun decrement() {
        api.decrement()
    }

    override fun getCount(): CounterEntity {
        val response = api.getCount()
        return CounterEntity(
            operationType = OperationType.fromString(response.operationType),
            count = response.count
        )
    }
}