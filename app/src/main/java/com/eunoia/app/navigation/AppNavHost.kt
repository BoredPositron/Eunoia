package com.eunoia.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eunoia.app.authentication.user_interface.AuthViewModel
import com.eunoia.app.authentication.user_interface.display.LoginScreen
import com.eunoia.app.authentication.user_interface.display.SignupScreen
import com.eunoia.app.user.user_interface.HomeScreen
import com.eunoia.app.user.user_interface.PickProfilePhoto
import com.eunoia.app.user.user_interface.RegistrationScreen
import com.eunoia.app.user.user_interface.viewmodel.ProfilePhotoViewModel
import com.eunoia.app.user.user_interface.viewmodel.UserViewModel

@Composable
fun AppNavHost(
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel,
    photoPickerViewModel: ProfilePhotoViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.Login.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.Login.route) {
            LoginScreen(authViewModel, navController)
        }
        composable(Routes.SignUp.route) {
            SignupScreen(authViewModel, navController)
        }
        composable(Routes.Home.route) {
            HomeScreen(authViewModel,navController)
        }
        composable(Routes.Registration.route){
            RegistrationScreen(userViewModel, navController)
        }
        composable(Routes.PickProfilePhoto.route){
            PickProfilePhoto(photoPickerViewModel, navController)
        }
    }
}