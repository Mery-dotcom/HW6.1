package com.example.hw61.data.repository

import com.example.hw61.data.datasource.EmulateService
import com.example.hw61.data.mapper.toDomain
import com.example.hw61.domain.model.CounterEntity
import com.example.hw61.domain.repository.CounterRepository

class CounterRepositoryImpl(
    private val api : EmulateService
) : CounterRepository {

    override fun increment() {
        api.increment()
    }

    override fun decrement() {
        api.decrement()
    }

    override fun getCount(): CounterEntity {
        val response = api.getCount()
        return response.toDomain()
    }
}