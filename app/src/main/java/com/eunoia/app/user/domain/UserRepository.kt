package com.eunoia.app.user.domain

import android.net.Uri
import com.eunoia.app.utils.Response

interface UserRepository {
    suspend fun register(age: String, gender: String): Response<Boolean>
    suspend fun addProfilePhoto(imageUri: Uri): Response<Boolean>
}