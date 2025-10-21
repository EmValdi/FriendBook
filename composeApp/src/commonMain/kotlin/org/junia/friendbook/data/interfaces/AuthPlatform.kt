package org.junia.friendbook.data.interfaces

interface AuthPlatform {
    suspend fun signUp(email: String, password: String): Result<Unit>
    suspend fun logIn(email: String, password: String): Result<Unit>
    suspend fun currentUser(): String?
    suspend fun currentMail(): String?
    suspend fun logout(): Unit
    suspend fun changePassword(newPassword: String): Result<Unit>
}

expect fun getAuthPlatform(): AuthPlatform