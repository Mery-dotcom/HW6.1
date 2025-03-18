package com.example.hw61.data.model

sealed class Either<out L, out R> {

    data class Success<R>(val success: R) : Either<Nothing, R>()
    data class Error<L>(val error: L) : Either<L, Nothing>()
}