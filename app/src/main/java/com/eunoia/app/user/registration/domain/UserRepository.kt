package com.eunoia.app.user.registration.domain

import android.net.Uri
import com.eunoia.app.user.registration.data.model.User
import com.eunoia.app.utils.Response

interface UserRepository {
    suspend fun register(
        age: String,
        gender: String,
        primaryPronoun: String,
        secondaryPronoun: String,
        orientation: String,
        choice: String
    ): Response<Boolean>

    suspend fun uploadProfilePhoto(imageUri: Uri?): Response<Boolean>

    suspend fun getUserMatches(): List<User>

}