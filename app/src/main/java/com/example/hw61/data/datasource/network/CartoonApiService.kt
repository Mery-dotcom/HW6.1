package com.example.hw61.data.datasource.network

import com.example.hw61.data.model.cartoon.BaseResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CartoonApiService {

    @GET("https://rickandmortyapi.com/api/character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): BaseResponseDto
}