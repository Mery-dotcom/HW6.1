package com.example.hw61.utils

sealed class Either<out L, out R> {

    data class Success<R>(val success: R) : Either<Nothing, R>()
    data class Error<L>(val error: L) : Either<L, Nothing>()
}