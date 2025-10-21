package org.junia.friendbook.data.interfaces

interface FirestorePlatform {
    suspend fun getFriends(uid: String): Result<MutableList<friend>>
    suspend fun addFriend(friend: friend, uid: String): Result<Unit>
}

expect fun getFirestorePlatform(): FirestorePlatform