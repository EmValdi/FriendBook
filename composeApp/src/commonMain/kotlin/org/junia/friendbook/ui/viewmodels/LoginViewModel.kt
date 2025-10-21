package org.junia.friendbook.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import org.junia.friendbook.data.SessionData
import org.junia.friendbook.data.interfaces.getAuthPlatform
import org.junia.friendbook.data.interfaces.getFirestorePlatform
import org.junia.friendbook.ui.Strings

class LoginViewModel: ViewModel() {

    val auth = getAuthPlatform()
    val db = getFirestorePlatform()

    var loginResult by mutableStateOf<String?>(null)

    var email by mutableStateOf("")
    private set

    var password by mutableStateOf("")
    private set

    var emailError by mutableStateOf<String?>(null)
    private set

    var passwordError by mutableStateOf<String?>(null)
    private set

    fun onEmailChanged(newEmail: String) {
        email = newEmail
        emailError = null
    }

    fun onPasswordChanged(newPassword: String) {
        password = newPassword
        passwordError = null
    }

    fun isValidEmail(): Boolean {
        if(email.isBlank()){
            emailError = Strings.blankEmailError
            return false
        }
        return true
    }

    fun isValidPassword(): Boolean{
        if(password.isBlank()){
            passwordError = Strings.blankPasswordError
            return false
        }
        return true
    }

    fun clearLoginResult() {
        loginResult = null
    }

    fun onLoginClicked() {
        if (!isValidEmail()  || !isValidPassword()) {
            return
        }
        kotlinx.coroutines.MainScope().launch {
            val result = auth.logIn(email, password)
            loginResult = result.fold(
                onSuccess = {
                    SessionData.currentUid = auth.currentUser()!!
                    SessionData.userMail = auth.currentMail()!!
                    val result = db.getUserName(SessionData.currentUid)
                    SessionData.userName = result.getOrDefault("Unknown")
                    "Success"
                            },
                onFailure = { it.message ?: "Unknown Error, Try again later" }
            )
        }
    }

}