package com.eunoia.app.user.registration.data.constants

import com.eunoia.app.R

sealed class Genders (var gender: String, var icon: Int?) {
    object Male: Genders("Male", R.drawable.male)
    object Female: Genders("Female", R.drawable.female)
    object TransGender: Genders("Trans-Gender", R.drawable.transgender)
    object Agender: Genders("A-Gender", R.drawable.agender)
}