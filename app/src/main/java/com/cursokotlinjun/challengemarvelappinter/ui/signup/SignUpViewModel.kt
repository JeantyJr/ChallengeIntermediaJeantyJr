package com.cursokotlinjun.challengemarvelappinter.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cursokotlinjun.challengemarvelappinter.util.extensions.isEmailValid
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _signUpState

    fun updateSignUpState(
        userName: String,
        email: String,
        password: String,
        confirmedPassword: String
    ) {
        when {
            userName.length < 5-> _signUpState.value =
                SignUpState(isUserNameValid = true)
            !isEmailValid(email) -> _signUpState.value =
                SignUpState(isEmailValid = true)
            password.length <= 5 -> _signUpState.value =
                SignUpState(isPasswordValid = true)
            (confirmedPassword != password) -> _signUpState.value =
                SignUpState(isConfirmedPasswordValid = true)
            else -> _signUpState.value = SignUpState(isAllFieldValid = true)
        }

    }
}

data class SignUpState(
    val isUserNameValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isConfirmedPasswordValid: Boolean = false,
    val isAllFieldValid: Boolean = false
)