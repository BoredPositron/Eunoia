package com.eunoia.app.user.registration.data.repository

import android.net.Uri
import com.eunoia.app.user.registration.data.model.User
import com.eunoia.app.user.registration.domain.UserRepository
import com.eunoia.app.utils.Response
import com.eunoia.app.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseFirestore,
    private val cloudStorage: FirebaseStorage,
) : UserRepository {
    private var _users: MutableList<User> = mutableListOf()
    var users: List<User> = _users
    private val user = auth.currentUser

    override suspend fun register(
        age: String,
        gender: String,
        primaryPronoun: String,
        secondaryPronoun: String,
        orientation: String,
        choice: String
    ): Response<Boolean> {
        return try {
            val uid = user!!.uid
            val email = user.email.toString()
            val name = user.displayName.toString()
            val user = User(
                uid = uid,
                email = email,
                name = name,
                age = age,
                gender = gender,
                primaryPronoun = primaryPronoun,
                secondaryPronoun = secondaryPronoun,
                orientation = orientation,
            )
            database.collection("USERS").document(uid).set(user).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e.message ?: "Error")
        }
    }


    override suspend fun uploadProfilePhoto(imageUri: Uri?): Response<Boolean> {
        return try {
            val uid = user!!.uid
            val storageRef = cloudStorage.reference.child("USERS/${uid}/profilephoto")
            val uploadTask = storageRef.putFile(imageUri!!)
            uploadTask.await()
            val downloadUrl = storageRef.downloadUrl.await()
            val userDocRef = database.collection("users").document(uid)
            val userData = mapOf("profilePhotoUrl" to downloadUrl.toString())
            userDocRef.set(userData, SetOptions.merge()).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e.message ?: "Error")
        }
    }

    override suspend fun getUserMatches(): List<User> {
        try {
            val result = database.collection("USERS")
                .whereNotEqualTo("uid", user!!.uid)
                .get()
                .await()
            for (user in result) {
                _users.add(user.toObject(User::class.java))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return users
    }
}