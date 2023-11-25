package com.eunoia.app.authentication.user_interface.display

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eunoia.app.R
import com.eunoia.app.authentication.user_interface.AuthViewModel
import com.eunoia.app.navigation.Routes
import com.eunoia.app.ui.theme.themeBlack
import com.eunoia.app.ui.theme.themeDarkBlue
import com.eunoia.app.ui.theme.themeRed
import com.eunoia.app.ui.theme.themeWhite
import com.eunoia.app.utils.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(viewModel: AuthViewModel?, navController: NavController?) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    val loginFlow = viewModel?.signupFlow?.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign Up",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = stringResource(id = R.string.ENTER_EMAIL))
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = themeRed,
                unfocusedBorderColor = themeBlack,
                unfocusedTrailingIconColor = themeBlack,
                focusedTrailingIconColor = themeDarkBlue
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.8F),
            trailingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(text = stringResource(id = R.string.ENTER_NAME))
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = themeRed,
                unfocusedBorderColor = themeBlack,
                unfocusedTrailingIconColor = themeBlack,
                focusedTrailingIconColor = themeDarkBlue
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.8F),
            trailingIcon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = stringResource(id = R.string.ENTER_PASSWORD))
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = themeRed,
                unfocusedBorderColor = themeBlack,
                unfocusedTrailingIconColor = themeBlack,
                focusedTrailingIconColor = themeDarkBlue
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.8F),
            trailingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
        ElevatedButton(
            onClick = {
                viewModel?.signup(name, email, password)
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = themeBlack,
                contentColor = themeWhite
            )
        ) {
            Text(
                text = stringResource(id = R.string.PROCEED),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
        }
    }
    loginFlow?.value?.let {
        when (it) {
            is Resource.Failure -> {
                val context = LocalContext.current
                Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
            }

            Resource.Loading -> {
                CircularProgressIndicator()
            }

            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    navController?.navigate(Routes.Registration.route) {
                        popUpTo(Routes.Registration.route) { inclusive = true }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun previewSignUp() {
    SignupScreen(viewModel = null, navController = null)
}

