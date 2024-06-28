package dev.sudhanshu.rickymorty.data.remote

import dev.sudhanshu.rickymorty.domain.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character/")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse
}