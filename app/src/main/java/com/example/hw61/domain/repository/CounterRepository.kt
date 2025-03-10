package com.example.hw61.domain.repository

interface CounterRepository {

    fun increment()

    fun decrement()

    fun getCount(): CounterRepository
}