package org.junia.friendbook.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.junia.friendbook.data.Hobby
import org.junia.friendbook.data.interfaces.getFirestorePlatform
import org.junia.friendbook.ui.views.FriendbookScreen

class AddhobbyViewModel: ViewModel() {
    private val db = getFirestorePlatform()

    var name by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    fun onNameChange(newValue: String) { name = newValue }

    fun onDescriptionChange(newValue: String) { description = newValue }

    fun addHobby(navController: NavController) {
        viewModelScope.launch {
            val newHobby = Hobby(
                name = name,
                description = description
            )

            val result = db.addHobby(newHobby)

            result.onSuccess {
                navController.navigate(FriendbookScreen.Hobbylist.name)
            }.onFailure { e ->
                println("Error adding hobby: $e")
            }
        }
    }
}
