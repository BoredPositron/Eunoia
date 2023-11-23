package com.eunoia.app.user.user_interface

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.eunoia.app.R
import com.eunoia.app.authentication.user_interface.AuthViewModel
import com.eunoia.app.navigation.ROUTE_HOME
import com.eunoia.app.navigation.ROUTE_LOGIN

@Composable
fun HomeScreen(viewModel: AuthViewModel?, navController: NavHostController?){
    Column {
        Button(
            onClick = {
                viewModel?.logout()
                navController!!.navigate(ROUTE_LOGIN){
                    popUpTo(ROUTE_HOME){inclusive = true}
                }
            }
        ) {
            Text(text = stringResource(id = R.string.LOGOUT))
        }
    }
}