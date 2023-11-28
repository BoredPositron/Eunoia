package com.eunoia.app.user.registration.domain

import android.net.Uri
import com.eunoia.app.utils.Response

interface UserRepository {
    suspend fun register(age: String, gender: String): Response<Boolean>
    suspend fun uploadProfilePhoto(imageUri: Uri): Response<Boolean>
}