package org.junia.friendbook.data.interfaces

interface AuthPlatform {
    suspend fun signUp(email: String, password: String): Result<Unit>
    suspend fun logIn(email: String, password: String): Result<Unit>
}

expect fun getAuthPlatform(): AuthPlatform