package org.junia.friendbook.data.interfaces

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.util.fastMapNotNull
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import android.content.Context
import kotlinx.coroutines.tasks.await

class AndroidFirestorePlatform: FirestorePlatform{
    val db = Firebase.firestore

    override suspend fun getFriends(uid: String): Result<MutableList<friend>> {
        return try {
            val documents = db.collection("friends")
                .whereEqualTo("source_uid", uid)
                .get()
                .await()

            var friendsList: MutableList<friend> = mutableStateListOf()

            for (document in documents) {
                Log.d(TAG, "${document.id} => ${document.data}")
                val friend = document.toObject<friend>()
                friendsList.add(friend)
            }

            Log.d("Result", documents.toString())
            Result.success(friendsList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}

actual fun getFirestorePlatform(): FirestorePlatform = AndroidFirestorePlatform()

