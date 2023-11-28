package com.eunoia.app.authentication.user_interface.display

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
<<<<<<< Updated upstream
=======
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
>>>>>>> Stashed changes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
<<<<<<< Updated upstream
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
=======
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
>>>>>>> Stashed changes
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.eunoia.app.R
import com.eunoia.app.authentication.user_interface.AuthViewModel
import com.eunoia.app.navigation.Routes
import com.eunoia.app.utils.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: AuthViewModel?, navController: NavController?) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginFlow = viewModel?.loginFlow?.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = stringResource(id = R.string.ENTER_EMAIL))
            },
<<<<<<< Updated upstream
            colors = TextFieldDefaults.textFieldColors(),
=======
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = themeRed,
                unfocusedBorderColor = themeBlack,
                unfocusedTrailingIconColor = themeBlack,
                focusedTrailingIconColor = themeRed,
                focusedLabelColor = themeRed,
                textColor = themeBlack
            ),
>>>>>>> Stashed changes
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            shape = CircleShape
        )
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = stringResource(id = R.string.ENTER_PASSWORD))
            },
<<<<<<< Updated upstream
            colors = TextFieldDefaults.textFieldColors(),
=======
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = themeRed,
                unfocusedBorderColor = themeBlack,
                unfocusedTrailingIconColor = themeBlack,
                focusedTrailingIconColor = themeRed,
                focusedLabelColor = themeRed,
                textColor = themeBlack
            ),
>>>>>>> Stashed changes
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            shape = CircleShape
        )

        Button(
            onClick = {
                viewModel?.login(email, password)
            }
        ) {
            Text(text = stringResource(id = R.string.LOGINTEXT))
        }
        Text(
            text = "Sign Up",
            modifier = Modifier.clickable{
                navController!!.navigate(Routes.SignUp.route){
                    popUpTo(Routes.Login.route){inclusive = true}
                }
            })
    }
    loginFlow?.value?.let {
        when (it) {
            is Resource.Failure -> {
                val context = LocalContext.current
                Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
            }

            Resource.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ){
                    Card (
                        modifier = Modifier
                            .background(Color.Transparent)
                            .width(200.dp)
                            .height(200.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ){
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(200.dp)
                                .padding(20.dp),
                            strokeWidth = 10.dp,

                        )
                    }
                }
            }

            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    navController?.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun preview() {
    LoginScreen(viewModel = null, navController = null)
}