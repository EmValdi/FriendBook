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
import com.google.android.gms.common.util.CollectionUtils.mapOf
import kotlinx.coroutines.tasks.await
import org.junia.friendbook.data.Hobby

class AndroidFirestorePlatform: FirestorePlatform {
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
                friend.id = document.id
                friendsList.add(friend)
            }

            Log.d("Result", documents.toString())
            Result.success(friendsList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addFriend(friend: friend, uid: String): Result<Unit> {
        return try {
            val friendData = hashMapOf(
                "name" to friend.name,
                "country" to friend.country,
                "phone_number" to friend.phone_number,
                "instagram" to friend.instagram,
                "school" to friend.school,
                "hobbies" to friend.hobbies,
                "source_uid" to uid
            )
            db
                .collection("friends")
                .add(friendData)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun editFriend(friend: friend, id: String): Result<Unit> {
        return try {
            val friendData = hashMapOf(
                "name" to friend.name,
                "country" to friend.country,
                "phone_number" to friend.phone_number,
                "instagram" to friend.instagram,
                "school" to friend.school,
                "hobbies" to friend.hobbies
            )

            //Log.d("Document",documentId)

            db.collection("friends")
                .document(id)
                .update(friendData)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteFriend(id: String): Result<Unit> {
        return try {
            db.collection("friends")
                .document(id)
                .delete()
                .await()  // Espera a que la operación termine

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getHobbies(): Result<MutableList<Hobby>> {
        return try {
            val documents = db.collection("hobbies")
                .get()
                .await()

            var hobbyList: MutableList<Hobby> = mutableStateListOf()

            for (document in documents) {
                Log.d(TAG, "${document.id} => ${document.data}")
                val hobby = document.toObject<Hobby>()
                hobby.id = document.id
                hobbyList.add(hobby)
            }

            Result.success(hobbyList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addHobby(hobby: Hobby): Result<Unit> {
        return try {
            val data = hashMapOf(
                "name" to hobby.name,
                "description" to hobby.description
            )

            db.collection("hobbies")
                .add(data)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun editHobby(hobby: Hobby, id: String): Result<Unit> {
        return try {
            val data: Map<String, Any> = hashMapOf(
                "name" to hobby.name,
                "description" to hobby.description
            )

            db.collection("hobbies")
                .document(id)
                .update(data)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteHobby(id: String): Result<Unit> {
        return try {
            db.collection("hobbies")
                .document(id)
                .delete()
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}

actual fun getFirestorePlatform(): FirestorePlatform = AndroidFirestorePlatform()

