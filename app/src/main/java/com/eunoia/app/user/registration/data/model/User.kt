package com.eunoia.app.user.registration.data.model

data class User(
    var uid: String = "",
    var name: String = "",
    var email: String = "",
    var age: String = "",
    var gender: String = "",
    var primaryPronoun: String = "",
    var secondaryPronoun: String = ""
)