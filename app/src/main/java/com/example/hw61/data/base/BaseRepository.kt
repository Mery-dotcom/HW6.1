package com.example.hw61.data.base

import com.example.hw61.utils.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.lang.Exception

abstract class BaseRepository {

    protected fun <T> doRequest(
        request: suspend () -> Response<T>
    ): Flow<Either<Throwable, T>> {
        return flow {
            try {
                val response = request()
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let { data ->
                        emit(Either.Success(data))
                    }
                } else {
                    emit(Either.Error(Throwable("API call failed: " +
                            "${response.code()} - ${response.message()}")))
                }
            } catch (e: Exception) {
                emit(Either.Error(error = e))
            }
        }
    }
}