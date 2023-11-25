package com.eunoia.app.user.user_interface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.eunoia.app.R
import com.eunoia.app.authentication.user_interface.AuthViewModel
import com.eunoia.app.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: AuthViewModel?,
    navController: NavHostController?
) {
    val items = listOf(
        Routes.Home,
        Routes.Profile
    )
    Scaffold(
    ) {
        Column(
            Modifier
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Button(
                modifier = Modifier.padding(it),
                onClick = {
                    viewModel?.logout()
                    navController!!.navigate(Routes.Login.route) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                },
            ) {
                Text(text = stringResource(id = R.string.LOGOUT))
            }
//            Card(
//                modifier = Modifier
//                    .width(200.dp)
//                    .height(200.dp),
//                shape = CircleShape
//            ) {
//                AsyncImage(
//                    model = viewModel!!.currentUser!!.photoUrl,
//                    contentDescription = null,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clip(CircleShape),
//                    contentScale = ContentScale.Crop
//                )
//            }
        }
    }
}