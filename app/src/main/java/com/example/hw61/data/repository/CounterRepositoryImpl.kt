package com.example.hw61.data.repository

import com.example.hw61.data.datasource.EmulateService
import com.example.hw61.data.mapper.toDomain
import com.example.hw61.domain.model.CounterEntity
import com.example.hw61.domain.repository.CounterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CounterRepositoryImpl(
    private val api: EmulateService,
    private val dispatcher: CoroutineDispatcher
) : CounterRepository {

    override suspend fun increment() {
        withContext(dispatcher) {
            api.increment()
        }
    }

        override suspend fun decrement() {
            withContext(dispatcher) {
            api.decrement()
            }
        }

        override suspend fun getCount(): CounterEntity {
            return withContext(dispatcher) {
            val response = api.getCount()
                response.toDomain()
            }
        }
    }