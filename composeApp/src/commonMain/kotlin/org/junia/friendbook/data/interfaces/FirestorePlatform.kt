package org.junia.friendbook.data.interfaces

interface FirestorePlatform {
    suspend fun getFriends(uid: String): Result<MutableList<friend>>
    //suspend fun sendFriend(email: String, password: String): Result<Unit>
}

expect fun getFirestorePlatform(): FirestorePlatform