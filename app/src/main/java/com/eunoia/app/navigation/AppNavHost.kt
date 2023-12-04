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
import com.eunoia.app.user.registration.user_interface.HomeScreen
import com.eunoia.app.user.registration.user_interface.Matches
import com.eunoia.app.user.registration.user_interface.PickProfilePhoto
import com.eunoia.app.user.registration.user_interface.RegistrationScreen
import com.eunoia.app.user.registration.user_interface.viewmodel.HomeViewModel
import com.eunoia.app.user.registration.user_interface.viewmodel.ProfilePhotoViewModel
import com.eunoia.app.user.registration.user_interface.viewmodel.UserViewModel

@Composable
fun AppNavHost(
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel,
    photoPickerViewModel: ProfilePhotoViewModel,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.Login.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Routes.Login.route,
        ) {
            LoginScreen(authViewModel, navController)
        }
        composable(
            route = Routes.SignUp.route,
        ) {
            SignupScreen(authViewModel, navController)
        }
        composable(
            route = Routes.Home.route,
        ) {
            HomeScreen(authViewModel, navController)
        }
        composable(
            route = Routes.Registration.route,
        ) {
            RegistrationScreen(userViewModel, navController)
        }
        composable(
            route = Routes.PickProfilePhoto.route,
        ) {
            PickProfilePhoto(photoPickerViewModel, navController)
        }
        composable(
            route = Routes.Matches.route
        ){
            Matches(viewModel = homeViewModel)
        }
    }
}