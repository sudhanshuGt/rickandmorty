package dev.sudhanshu.rickymorty.domain.usecase

import dev.sudhanshu.rickymorty.domain.CharacterResponse
import dev.sudhanshu.rickymorty.domain.repository.CharacterRepository
import javax.inject.Inject


class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(page: Int): CharacterResponse {
        return repository.getCharacters(page)
    }
}