package dev.sudhanshu.rickymorty.data.repository

import dev.sudhanshu.rickymorty.data.remote.RickAndMortyApi
import dev.sudhanshu.rickymorty.domain.CharacterResponse
import dev.sudhanshu.rickymorty.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): CharacterResponse {
        return api.getCharacters(page)
    }
}