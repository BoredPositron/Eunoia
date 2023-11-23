package com.eunoia.app.user.user_interface

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.eunoia.app.R
import com.eunoia.app.authentication.user_interface.AuthViewModel
import com.eunoia.app.navigation.ROUTE_HOME
import com.eunoia.app.navigation.ROUTE_LOGIN

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: AuthViewModel?, navController: NavHostController?){
    Scaffold(
        bottomBar = {
            BottomBar(viewModel = viewModel, navHostController = navController)
        }
    ) {
        Button(
            modifier = Modifier.padding(it),
            onClick = {
                viewModel?.logout()
                navController!!.navigate(ROUTE_LOGIN) {
                    popUpTo(ROUTE_HOME) { inclusive = true }
                }
            }
        ) {
            Text(text = stringResource(id = R.string.LOGOUT))
        }
    }
}