package com.eunoia.app.user.registration.data.repository

import android.net.Uri
import com.eunoia.app.user.registration.data.model.User
import com.eunoia.app.user.registration.domain.UserRepository
import com.eunoia.app.utils.Response
import com.eunoia.app.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
    private val cloudStorage: FirebaseStorage
) : UserRepository {


    override suspend fun register(
        age: String,
        gender: String,
        primaryPronoun: String,
        secondaryPronoun: String
    ): Response<Boolean> {
        return try {
            val uid = auth.currentUser?.uid.toString()
            val email = auth.currentUser?.email.toString()
            val name = auth.currentUser?.displayName.toString()
            val user = User(
                uid = uid,
                email = email,
                name = name,
                age = age,
                gender = gender,
                primaryPronoun = primaryPronoun,
                secondaryPronoun = secondaryPronoun
            )
            val result = database.getReference("USERS").child(uid.toString()).setValue(user).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e.message ?: "Error")
        }
    }


    override suspend fun uploadProfilePhoto(imageUri: Uri): Response<Boolean> {
        return try {
            val uid = auth.currentUser!!.uid.toString()
            val storageRef =
                cloudStorage.reference
                    .child("USERS/${uid}/profilephoto")
            storageRef.putFile(imageUri).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e.message ?: "Error")
        }
    }
}