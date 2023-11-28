package com.eunoia.app.user.registration.data.model

import android.net.Uri

data class User(
    var uid: String = "",
    var name: String = "",
    var email: String = "",
    var age: String = "",
    var gender: String = "",
    var profilePhotoUrl: Uri? = null
)