package com.example.hw61.domain.repository

import com.example.hw61.domain.model.CounterEntity

interface CounterRepository {

    fun increment()

    fun decrement()

    fun getCount(): CounterEntity
}