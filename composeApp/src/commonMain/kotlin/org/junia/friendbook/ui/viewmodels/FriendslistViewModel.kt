package org.junia.friendbook.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.junia.friendbook.data.SessionData
import org.junia.friendbook.data.interfaces.friend
import org.junia.friendbook.data.interfaces.getFirestorePlatform
import org.junia.friendbook.ui.views.FriendbookScreen

class FriendslistViewModel: ViewModel() {

    var userFriendsList by mutableStateOf<List<friend>>(emptyList())
        private set

    var currentDetail by mutableStateOf(friend("","","","","","", emptyList(),"",""))

    var name by mutableStateOf(currentDetail.name)
        private set

    var country by mutableStateOf(currentDetail.country)
        private set

    var phoneNumber by mutableStateOf(currentDetail.phone_number)
        private set

    var instagram by mutableStateOf(currentDetail.instagram)
        private set

    var school by mutableStateOf(currentDetail.school)
        private set

    var hobbies by mutableStateOf(currentDetail.hobbies)
        private set

    var id = currentDetail.id

    fun onNameChange(newValue: String) { name = newValue }
    fun onCountryChange(newValue: String) { country = newValue }
    fun onPhoneNumberChange(newValue: String) { phoneNumber = newValue }
    fun onInstagramChange(newValue: String) { instagram = newValue }
    fun onSchoolChange(newValue: String) { school = newValue }
    fun onHobbiesChange(newValue: String) {
        hobbies = newValue.split(",")?.map { it.trim() } ?: emptyList()
    }

    val db = getFirestorePlatform()

    fun loadFriends() {
        kotlinx.coroutines.MainScope().launch {
            val result = db.getFriends(SessionData.currentUid)
            userFriendsList = result.getOrNull()?: emptyList()
        }
    }

    fun setDetail(name: String?) {
        currentDetail = userFriendsList.find { it.name == name } ?: friend("", "", "", "")
    }

    fun editFriend(navController: NavController) {
        kotlinx.coroutines.MainScope().launch {
            val newFriend = friend(
                name = name!!,
                country = country!!,
                phone_number = phoneNumber!!,
                instagram = instagram!!,
                school = school!!,
                hobbies = hobbies!!,
                // Si no los manejas todavía, los dejamos con valores por defecto:
                fav_pokemon = "",
                picture = ""
            )

            val result = db.editFriend(newFriend, currentDetail.id)

            result.onSuccess {
                navController.navigate(FriendbookScreen.Friendlist.name)
                currentDetail = friend("")
            }.onFailure { e ->
                println(id)
                println(e)
            }
        }
    }

}