package com.eunoia.app.user.domain

import com.eunoia.app.utils.Resource
import com.eunoia.app.utils.Response

interface UserRepository {
    suspend fun register(age: String, gender: String): Response<Boolean>
}