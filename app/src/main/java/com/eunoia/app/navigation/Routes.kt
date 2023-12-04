package com.eunoia.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Routes(var title: String, var icon: ImageVector?, var route: String){
    object Home: Routes("Home", Icons.Default.Home, "home")
    object Login: Routes("Login",null, "login" )
    object SignUp: Routes("Sign Up", null, "signup")
    object Registration: Routes("Registration", null, "registration")
    object Profile: Routes("Profile", Icons.Default.Person, "profile")
    object PickProfilePhoto: Routes("Profile Photo", Icons.Default.Home, "profilephoto")
    object Matches: Routes("Matches", null, "matches")
}


