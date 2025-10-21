package org.junia.friendbook.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.junia.friendbook.data.Hobby
import org.junia.friendbook.data.SessionData
import org.junia.friendbook.data.interfaces.getFirestorePlatform
import org.junia.friendbook.ui.views.FriendbookScreen

class HobbyViewModel : ViewModel() {

    var HobbyList by mutableStateOf<List<Hobby>>(emptyList())
        private set

    var currentDetail by mutableStateOf(Hobby("", ""))

    var name by mutableStateOf(currentDetail.name)
        private set

    var description by mutableStateOf(currentDetail.description)
        private set

    val db = getFirestorePlatform()

    fun onNameChange(newValue: String) { name = newValue }
    fun onDescriptionChange(newValue: String) { description = newValue }

    fun getHobbies() {
        kotlinx.coroutines.MainScope().launch {
            val result = db.getHobbies()
            HobbyList = result.getOrNull() ?: emptyList()
        }
    }

    fun setDetail(name: String?) {
        currentDetail = HobbyList.find { it.name == name } ?: Hobby("", "")
    }

    fun editHobby(navController: NavController) {
        kotlinx.coroutines.MainScope().launch {
            val newHobby = Hobby(
                name = name,
                description = description
            )

            val result = db.editHobby(newHobby, currentDetail.id)

            result.onSuccess {
                navController.navigate(FriendbookScreen.Hobbylist.name)
                currentDetail = Hobby("", "")
            }.onFailure { e ->
                println(e)
            }
        }
    }

    fun deleteHobby(navController: NavController) {
        kotlinx.coroutines.MainScope().launch {
            val result = db.deleteHobby(currentDetail.id)

            result.onSuccess {
                navController.navigate(FriendbookScreen.Hobbylist.name)
                currentDetail = Hobby("","","")
            }.onFailure { e ->
                println(e)
            }
        }
    }
}