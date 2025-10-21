package org.junia.friendbook.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.junia.friendbook.data.SessionData
import org.junia.friendbook.data.interfaces.friend
import org.junia.friendbook.data.interfaces.getFirestorePlatform

class FriendslistViewModel: ViewModel() {

    var userFriendsList by mutableStateOf<List<friend>>(emptyList())
        private set

    var currentDetail by mutableStateOf<friend?>(null)
        private set

    val db = getFirestorePlatform()

    fun loadFriends() {
        kotlinx.coroutines.MainScope().launch {
            val result = db.getFriends(SessionData.currentUid)
            userFriendsList = result.getOrNull()?: emptyList()
        }
    }

    fun setDetail(name: String?) {
        val friend = userFriendsList.find{it.name == name}
        currentDetail = friend
    }

}