package com.eunoia.app.user.user_interface

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.eunoia.app.R
import com.eunoia.app.navigation.Routes
import com.eunoia.app.user.user_interface.viewmodel.UserViewModel
import com.eunoia.app.utils.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(viewModel: UserViewModel?, navController: NavController? ){
    var username by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    val userFlow = viewModel?.userFlow?.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = {
                Text(text = stringResource(id = R.string.ENTER_USERNAME))
            },
            colors = TextFieldDefaults.textFieldColors(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = CircleShape
        )
        OutlinedTextField(
            value = age,
            onValueChange = {
                age = it
            },
            label = {
                Text(text = stringResource(id = R.string.ENTER_AGE))
            },
            colors = TextFieldDefaults.textFieldColors(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            shape = CircleShape
        )
        OutlinedTextField(
            value = gender,
            onValueChange = {
                gender = it
            },
            label = {
                Text(text = stringResource(id = R.string.ENTER_GENDER))
            },
            colors = TextFieldDefaults.textFieldColors(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = CircleShape
        )
        Button(
            onClick = {
                viewModel?.register(age = age, gender = gender)
            }
        ) {
            Text(text = stringResource(id = R.string.LOGINTEXT))
        }
    }
    userFlow?.value?.let {
        when (it) {
            is Response.Error -> {
                val context = LocalContext.current
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

            Response.Loading -> {
                CircularProgressIndicator()
            }

            is Response.Success -> {
                LaunchedEffect(Unit) {
                    navController?.navigate(Routes.PickProfilePhoto.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            }
        }
    }
}

