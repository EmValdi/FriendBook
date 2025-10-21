package org.junia.friendbook.data.interfaces

import org.junia.friendbook.data.Hobby

interface FirestorePlatform {
    suspend fun getFriends(uid: String): Result<MutableList<friend>>
    suspend fun addFriend(friend: friend, uid: String): Result<Unit>
    suspend fun editFriend(friend: friend, id: String): Result<Unit>
    suspend fun deleteFriend(id: String): Result<Unit>

    suspend fun getHobbies(): Result<MutableList<Hobby>>
    suspend fun addHobby(hobby: Hobby): Result<Unit>
    suspend fun editHobby(hobby: Hobby, id: String): Result<Unit>
    suspend fun deleteHobby(id: String): Result<Unit>

    suspend fun setUserName(newUserName: String, uid: String): Result<Unit>
    suspend fun changeUserName(UserName: String, uid: String): Result<Unit>
    suspend fun getUserName(uid: String): Result<String>
}

expect fun getFirestorePlatform(): FirestorePlatform