@file:Suppress("DEPRECATION")

package com.eunoia.app.authentication.user_interface.display

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eunoia.app.R
import com.eunoia.app.authentication.user_interface.AuthViewModel
import com.eunoia.app.common.LoadingScreen.LoadingScreen
import com.eunoia.app.navigation.Routes
import com.eunoia.app.ui.theme.Raleway
import com.eunoia.app.ui.theme.themeBlack
import com.eunoia.app.ui.theme.themeDarkBlue
import com.eunoia.app.ui.theme.themeLightBlue
import com.eunoia.app.ui.theme.themeRed
import com.eunoia.app.ui.theme.themeWhite
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
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(id = R.string.app_name),
            fontSize = 60.sp,
            fontFamily = Raleway,
            fontWeight = FontWeight.Bold,
            color = themeDarkBlue
            )
        Text(
            stringResource(id = R.string.LOGINTEXT),
            fontSize = 40.sp,
            fontFamily = Raleway,
            fontWeight = FontWeight.Bold,
            color = themeDarkBlue
            )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                    Text(
                        stringResource(id = R.string.ENTER_EMAIL),
                        fontFamily = Raleway,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = themeLightBlue,
                unfocusedBorderColor = themeBlack,
                unfocusedTrailingIconColor = themeBlack,
                focusedTrailingIconColor = themeRed,
                focusedLabelColor = themeRed,
            ),

            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle(
                fontFamily = Raleway,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(65.dp),
            trailingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null)},
            singleLine = true
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(
                    text = stringResource(id = R.string.ENTER_PASSWORD),
                    fontFamily = Raleway,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = themeLightBlue,
                unfocusedBorderColor = themeBlack,
                unfocusedTrailingIconColor = themeBlack,
                focusedTrailingIconColor = themeRed,
                focusedLabelColor = themeRed,
            ),

            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle(
                fontFamily = Raleway,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(65.dp),
            trailingIcon = { Icon(imageVector = Icons.Default.Lock , contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(30.dp))
        ElevatedButton(
            onClick = {
                viewModel?.login(email, password)
            },
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(0.8f),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(3.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = themeBlack,
                contentColor = themeWhite
            )
        ) {
            Text(
                text = stringResource(id = R.string.LOGINTEXT),
                fontFamily = Raleway,
                fontWeight = FontWeight.ExtraBold,
                fontSize =20.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.SIGNUP_PROMPT),
            modifier = Modifier.clickable{
                navController!!.navigate(Routes.SignUp.route){
                    popUpTo(Routes.Login.route){inclusive = true}
                }
            },
            fontFamily = Raleway,
            fontWeight = FontWeight.SemiBold,
            color = themeDarkBlue
        )
    }
    loginFlow?.value?.let {
        when (it) {
            is Resource.Failure -> {
                val context = LocalContext.current
                Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
            }

            Resource.Loading -> {
                LoadingScreen()
            }

            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    navController?.navigate(Routes.Matches.route) {
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