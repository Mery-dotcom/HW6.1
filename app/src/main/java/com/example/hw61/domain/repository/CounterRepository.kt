package com.example.hw61.domain.repository

import com.example.hw61.domain.model.CounterEntity

interface CounterRepository {

    suspend fun increment()

    suspend fun decrement()

    suspend fun getCount(): CounterEntity
}