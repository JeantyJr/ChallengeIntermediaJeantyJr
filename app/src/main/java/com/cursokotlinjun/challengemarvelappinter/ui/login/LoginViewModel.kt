package com.cursokotlinjun.challengemarvelappinter.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cursokotlinjun.challengemarvelappinter.util.extensions.isEmailValid
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> =_loginState

    fun updateStateLogin(email: String, password: String){
     when{
         !isEmailValid(email) -> _loginState.value = LoginState(isEmailValid = true)
         password.length <= 5 -> _loginState.value = LoginState(isPasswordValid = true)
         else -> _loginState.value = LoginState(isAllFieldsValid = true)
     }
    }
}


    data class LoginState(
        val isEmailValid: Boolean = false,
        val isPasswordValid: Boolean = false,
        val isAllFieldsValid: Boolean = false
    )
