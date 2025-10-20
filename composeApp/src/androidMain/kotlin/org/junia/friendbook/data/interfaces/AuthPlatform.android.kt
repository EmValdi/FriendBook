package org.junia.friendbook.data.interfaces

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AndroidAuthPlatform : AuthPlatform {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override suspend fun signUp(email: String, password: String): Result<Unit> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logIn(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun currentUser(): String?{
        return auth.currentUser?.uid
    }
}

actual fun getAuthPlatform(): AuthPlatform = AndroidAuthPlatform()