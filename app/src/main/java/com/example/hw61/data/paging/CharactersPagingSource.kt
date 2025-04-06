package com.example.hw61.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hw61.data.datasource.network.CartoonApiService
import com.example.hw61.data.model.cartoon.CharacterDto
import com.example.hw61.domain.model.Character
import com.example.hw61.data.mapper.toDomain


const val START_INDEX = 1

class CharactersPagingSource(
    private val api: CartoonApiService,
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentKey = params.key ?: START_INDEX
            val response = api.getCharacters(currentKey)
            val characters = response.results?.map { it.toDomain() }.orEmpty()

            LoadResult.Page(
                data = characters,
                prevKey = if (currentKey == START_INDEX) null else currentKey - 1,
                nextKey = if (response.info?.next == null) null else currentKey + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}