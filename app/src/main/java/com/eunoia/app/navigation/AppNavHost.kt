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
import com.eunoia.app.user.user_interface.RegistrationScreen
import com.eunoia.app.user.user_interface.viewmodel.UserViewModel

@Composable
fun AppNavHost(
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_LOGIN
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ROUTE_LOGIN) {
            LoginScreen(authViewModel, navController)
        }
        composable(ROUTE_SIGNUP) {
            SignupScreen(authViewModel, navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(authViewModel, navController)
        }
        composable(ROUTE_REG){
            RegistrationScreen(userViewModel, navController)
        }
    }
}