package com.eunoia.app.user.data.repository

import android.net.Uri
import com.eunoia.app.user.data.model.User
import com.eunoia.app.user.domain.UserRepository
import com.eunoia.app.utils.Response
import com.eunoia.app.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
): UserRepository{
    override suspend fun register(
        age: String,
        gender: String
    ): Response<Boolean> {
        return try{
            val uid = auth.currentUser?.uid.toString()
            val email = auth.currentUser?.email.toString()
            val name = auth.currentUser?.displayName.toString()
            val user = User(
                uid = uid,
                email = email,
                name = name,
                age = age,
                gender = gender
            )
            val result = database.getReference("USERS").child(uid.toString()).setValue(user).await()
            Response.Success(true)
        }catch (e: Exception){
            Response.Error(e.message?: "Error")
        }
    }

    override suspend fun addProfilePhoto(imageUri: Uri): Response<Boolean> {
        return try{
            val Result = auth.currentUser!!.updateProfile(
                UserProfileChangeRequest.Builder().setPhotoUri(imageUri).build())
                .await()
            Response.Success(true)
        }catch(e: Exception){
            Response.Error(e.message?: "Error")
        }
    }

}