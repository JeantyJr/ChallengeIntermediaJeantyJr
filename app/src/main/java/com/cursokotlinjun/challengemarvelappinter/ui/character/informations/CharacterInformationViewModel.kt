package com.cursokotlinjun.challengemarvelappinter.ui.character.informations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursokotlinjun.challengemarvelappinter.data.data_source.Ressource
import com.cursokotlinjun.challengemarvelappinter.domain.model.Comic
import com.cursokotlinjun.challengemarvelappinter.domain.repository.CharacterRepositoryImpl
import com.cursokotlinjun.challengemarvelappinter.domain.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CharacterInformationViewModel @Inject constructor(
    private val charactersRepository: CharacterRepositoryImpl
): ViewModel() {
    private val _comicStatus = MutableLiveData<ComicsStatus>()
    val comicStatus: LiveData<ComicsStatus> = _comicStatus

    fun getComics(characterId: Int){
        _comicStatus.value = ComicsStatus(isLoading = true)
        viewModelScope.launch(Dispatchers.Main){
            withContext(Dispatchers.IO){
                charactersRepository.getAllComics(characterId)
            }.run {
                when(status){
//                    Ressource.Status.LOADING -> {
//                        _comicStatus.value = ComicsStatus(isLoading = true)
//
//                    }
                    Ressource.Status.SUCCESS -> {
                        _comicStatus.value = ComicsStatus(isSuccess = this.data?: emptyList())
                    }
                    Ressource.Status.ERROR -> {
                        _comicStatus.value = ComicsStatus(isError = true)
                    }

                }
            }
        }
    }






//    sealed class ComicsStatus{
//        data class Success(val comics: List<Comic>): ComicsStatus()
//        data class Loading(val isLoading: Boolean): ComicsStatus ()
//        data class Error(val message: String): ComicsStatus()
//    }
}
data class ComicsStatus(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: List<Comic> = emptyList()
)