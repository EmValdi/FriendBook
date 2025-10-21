package org.junia.friendbook.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.junia.friendbook.data.SessionData
import org.junia.friendbook.data.interfaces.getAuthPlatform
import org.junia.friendbook.data.interfaces.getFirestorePlatform
import org.junia.friendbook.ui.views.FriendbookScreen

class EditAccountViewModel: ViewModel() {
    val auth = getAuthPlatform()
    val db = getFirestorePlatform()

    var password by  mutableStateOf("")  // empty = unchanged
    var userName by  mutableStateOf("")
    var showPassword by  mutableStateOf(false)

    fun changePassword(navController: NavController){
        kotlinx.coroutines.MainScope().launch {
            val result = auth.changePassword(password)
            result.onSuccess {
                navController.navigate(FriendbookScreen.Account.name)
            }.onFailure { e ->
                println(e)
            }
        }
    }

    fun changeUserName(navController: NavController){
        kotlinx.coroutines.MainScope().launch {
            val result = db.changeUserName(userName,SessionData.currentUid)
            result.onSuccess {
                navController.navigate(FriendbookScreen.Account.name)
            }.onFailure { e ->
                println(e)
            }
        }
    }
}