package com.eunoia.app.user.user_interface

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.eunoia.app.authentication.user_interface.AuthViewModel
import com.eunoia.app.navigation.ROUTE_HOME
import com.eunoia.app.navigation.ROUTE_LOGIN

@Composable
fun BottomBar(viewModel: AuthViewModel?, navHostController: NavHostController?) {

    NavigationBar(
        modifier = Modifier.height(60.dp)
    ) {
        NavigationBarItem(
            label = {
                ROUTE_HOME
            },
            selected = true,
            onClick = { go_home(navHostController) },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) }
        )
        NavigationBarItem(
            label = {
                ROUTE_LOGIN
            },
            selected = false,
            onClick = { log_out(navHostController, viewModel) },
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) }
        )
    }
}

fun log_out(navHostController: NavHostController?, viewModel: AuthViewModel?) {
    viewModel!!.logout()
    navHostController!!.navigate(ROUTE_LOGIN) {
        popUpTo(ROUTE_HOME) { inclusive = true }
    }
}

fun go_home(navController: NavHostController?) {
    navController!!.navigate(ROUTE_HOME) {
        popUpTo(ROUTE_HOME) { inclusive = true }
    }
}