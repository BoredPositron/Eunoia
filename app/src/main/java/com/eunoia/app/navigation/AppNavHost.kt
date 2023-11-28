package com.eunoia.app.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eunoia.app.authentication.user_interface.AuthViewModel
import com.eunoia.app.authentication.user_interface.display.LoginScreen
import com.eunoia.app.authentication.user_interface.display.SignupScreen
import com.eunoia.app.user.registration.user_interface.HomeScreen
import com.eunoia.app.user.registration.user_interface.PickProfilePhoto
import com.eunoia.app.user.registration.user_interface.RegistrationScreen
import com.eunoia.app.user.registration.user_interface.viewmodel.ProfilePhotoViewModel
import com.eunoia.app.user.registration.user_interface.viewmodel.UserViewModel

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
        composable(
            route = Routes.Home.route,
            enterTransition = {
                slideInVertically(
                    animationSpec = tween(700),
                    initialOffsetY = {-500}
                )
            }) {
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