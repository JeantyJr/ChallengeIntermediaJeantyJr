package com.cursokotlinjun.challengemarvelappinter.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursokotlinjun.challengemarvelappinter.data.data_source.Ressource
import com.cursokotlinjun.challengemarvelappinter.domain.model.Character
import com.cursokotlinjun.challengemarvelappinter.domain.repository.CharacterRepositoryImpl
import com.cursokotlinjun.challengemarvelappinter.domain.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject



@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val charactersRepository: CharacterRepositoryImpl
): ViewModel() {

    private val _charactersStatus = MutableLiveData<CharacterStatus>()
    val charactersStatus: LiveData<CharacterStatus> = _charactersStatus
    private var limitOffset = 0

    init{
        loadCharacters(limitOffset)
    }

    private fun loadCharacters(offset:Int) {
        _charactersStatus.value = CharacterStatus(isLoading = true)
        viewModelScope.launch(Dispatchers.Main){
            withContext(Dispatchers.IO){
                charactersRepository.getAllCharacters(offset)
            }.run {
                when(status){
                    Ressource.Status.LOADING -> {
                        _charactersStatus.value = CharacterStatus(isLoading = true)

                    }
                    Ressource.Status.ERROR -> {
                        _charactersStatus.value = CharacterStatus(isError = true)

                    }
                    Ressource.Status.SUCCESS -> {
                        _charactersStatus.value = CharacterStatus(isSucces = this.data?: emptyList())
                    }
                }
            }
        }

    }

    fun moreCharactersLoad(){
        limitOffset += 15
        loadCharacters(limitOffset)


    }




data class CharacterStatus(
    val isSucces: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
    )
}