package dev.sudhanshu.rickymorty.domain.repository

import dev.sudhanshu.rickymorty.domain.CharacterResponse

interface CharacterRepository {
    suspend fun getCharacters(page: Int): CharacterResponse
}