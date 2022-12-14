package com.cursokotlinjun.challengemarvelappinter.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.cursokotlinjun.challengemarvelappinter.MainActivity
import com.cursokotlinjun.challengemarvelappinter.databinding.ActivityLoginBinding
import com.cursokotlinjun.challengemarvelappinter.ui.signup.SignUpActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    private val loginViewModel: LoginViewModel by viewModels()
    private val callBackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        //facebook login
        binding.loginButtonFacebook.setOnClickListener {
            loginWithFacebook()
        }

        binding.tvSignUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        onTextFieldChange()
        observeFieldState()

    }

    fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
// Login with email && Password
    override fun onStart() {
        super.onStart()
    val currentUser = auth.currentUser
    if (currentUser != null){
        reload()
    }
    }

    private fun signIn(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful){
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            } else {

            }

        }
    }

    //Login with Facebook

    private fun loginWithFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, callBackManager, listOf("email"))
        LoginManager.getInstance().registerCallback(callBackManager,
            object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    showSnackBar("you canceled your login")
                }

                override fun onError(error: FacebookException) {
                    showToast("try again")
                }

                override fun onSuccess(result: LoginResult) {
                    result.let {
                        val token = it.accessToken
                        val credential = FacebookAuthProvider.getCredential(token.token)
                        FirebaseAuth.getInstance().signInWithCredential(credential)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    startActivity(
                                        Intent(
                                            this@LoginActivity,
                                            MainActivity::class.java
                                        )
                                    )
                                } else {
                                    showSnackBar("The combination of Email and Password is not correct. Please retry.")
                                }
                            }
                    }
                }

            })
    }

    private fun onTextFieldChange() {
        binding.apply {
            edtEmail.doAfterTextChanged {
                loginViewModel.updateStateLogin(
                    edtEmail.text.toString().trim(),
                    edtPassword.text.toString().trim(),
                )
            }
            edtPassword.doAfterTextChanged {
                loginViewModel.updateStateLogin(
                    edtEmail.text.toString().trim(),
                    edtPassword.text.toString().trim(),
                )
            }
        }
    }

    private fun observeFieldState() {
        loginViewModel.loginState.observe(this) {
            with(binding) {
                when {
                    it.isEmailValid && edtEmail.text?.isNotBlank() == true -> edtEmail.error =
                        "Email not correct"
                    it.isPasswordValid && edtPassword.text?.isNotBlank() == true -> edtPassword.error =
                        "Password not correct"
                    else -> {
                        btnLogin.setOnClickListener {
                            signIn(
                                edtEmail.text.toString().trim(),
                                edtPassword.text.toString().trim()
                            )
                        }
                    }
                }
            }
        }
    }


    fun showSnackBar(msg: String) {
        val contextView = binding.btnLogin
        Snackbar.make(contextView, msg, Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun reload() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }
}