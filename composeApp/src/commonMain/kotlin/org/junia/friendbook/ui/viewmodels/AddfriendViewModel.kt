package org.junia.friendbook.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.junia.friendbook.data.SessionData
import org.junia.friendbook.data.interfaces.friend
import org.junia.friendbook.data.interfaces.getFirestorePlatform
import org.junia.friendbook.ui.views.FriendbookScreen

class AddfriendViewModel: ViewModel() {

    val db = getFirestorePlatform()

    var name by mutableStateOf("")
        private set

    var country by mutableStateOf("")
        private set

    var phoneNumber by mutableStateOf("")
        private set

    var instagram by mutableStateOf("")
        private set

    var school by mutableStateOf("")
        private set

    var hobbies by mutableStateOf(listOf<String>())
        private set

    //val errorMessage: StateFlow<String?> = _errorMessage

    fun onNameChange(newValue: String) { name = newValue }
    fun onCountryChange(newValue: String) { country = newValue }
    fun onPhoneNumberChange(newValue: String) { phoneNumber = newValue }
    fun onInstagramChange(newValue: String) { instagram = newValue }
    fun onSchoolChange(newValue: String) { school = newValue }
    fun onHobbiesChange(newValue: List<String>) {
        hobbies = newValue
    }

    fun addFriend(navController: NavController) {
        kotlinx.coroutines.MainScope().launch {
            val newFriend = friend(
                name = name,
                country = country,
                phone_number = phoneNumber,
                instagram = instagram,
                school = school,
                hobbies = hobbies,
                fav_pokemon = "",
                picture = ""
            )
            val result = db.addFriend(newFriend, SessionData.currentUid)

            result.onSuccess {
                navController.popBackStack()
            }.onFailure { e ->
                println(e)
            }
        }
    }

}