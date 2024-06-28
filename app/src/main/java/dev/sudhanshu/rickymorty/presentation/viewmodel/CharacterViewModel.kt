package dev.sudhanshu.rickymorty.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sudhanshu.rickymorty.domain.Character
import dev.sudhanshu.rickymorty.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private var currentPage = 1
    private var totalPageCount = 1

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        if (_isLoading.value || currentPage > totalPageCount) return

        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = getCharactersUseCase(currentPage)
                _characters.value += response.results
                currentPage++
                totalPageCount = response.info.pages
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
