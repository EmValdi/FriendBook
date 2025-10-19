package org.junia.friendbook.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import org.junia.friendbook.data.interfaces.getAuthPlatform
import org.junia.friendbook.ui.Strings

class SignupViewModel: ViewModel() {

    val auth = getAuthPlatform()

    var signupResult by mutableStateOf<String?>(null)

    var email by mutableStateOf("")
        private set
    var confirmEmail by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var emailError by mutableStateOf<String?>(null)
        private set

    var confirmEmailError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    fun onEmailChanged(newEmail: String) {
        email = newEmail
        emailError = null // limpia error al escribir
    }

    fun onConfirmEmailChanged(newConfirmEmail: String) {
        confirmEmail = newConfirmEmail
        confirmEmailError = null
    }

    fun onPasswordChanged(newPassword: String) {
        password = newPassword
        passwordError = null
    }

    fun isValidEmail(): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        if(confirmEmail.isBlank()){
            emailError = Strings.blankEmailError
            return false
        }
        else if(!emailRegex.matches(email)){
            emailError = Strings.invalidEmailError
            return false
        }
        emailError = null
        return true
    }

    fun isValidConfirmEmail(): Boolean {
        if(confirmEmail.isBlank()){
            confirmEmailError = Strings.blankConfirmEmailError
            return false
        }
        else if(email != confirmEmail){
            confirmEmailError = Strings.wrongConfirmEmailError
            return false
        }
        confirmEmailError = null
        return true
    }

    fun isValidPassword(): Boolean{
        if(password.isBlank()){
            passwordError = Strings.blankPasswordError
            return false
        }
        else if(password.length < 6){
            passwordError = Strings.invalidPasswordError
            return false
        }
        else{
            passwordError = null
            return true
        }
    }

    fun clearSignUpResult() {
        signupResult = null
    }

    fun onSignupClicked() {
        if (!isValidEmail() || !isValidConfirmEmail() || !isValidPassword()) {
            return
        }
        kotlinx.coroutines.MainScope().launch {
            val result = auth.signUp(email, password)
            signupResult = result.fold(
                onSuccess = { "Success" },
                onFailure = { it.message ?: "Unknown Error, Try again later" }
            )
        }
    }
}