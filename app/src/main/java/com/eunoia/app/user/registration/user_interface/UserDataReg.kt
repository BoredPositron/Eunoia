package com.eunoia.app.user.registration.user_interface

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eunoia.app.R
import com.eunoia.app.navigation.Routes
import com.eunoia.app.ui.theme.Raleway
import com.eunoia.app.ui.theme.themeBlack
import com.eunoia.app.ui.theme.themeRed
import com.eunoia.app.ui.theme.themeWhite
import com.eunoia.app.user.registration.user_interface.viewmodel.UserViewModel
import com.eunoia.app.utils.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(viewModel: UserViewModel?, navController: NavController?) {
    val age by remember { mutableStateOf("") }
    val userFlow = viewModel?.userFlow?.collectAsState()
    val genderList = listOf("Male", "Female", "Tran-Gender", "Non-Binary")
    var selectedGender by remember {
        mutableStateOf("")
    }
    val primaryPronounList = listOf("He", "She", "They")
    val secondaryPronounList = listOf("Him", "Her", "Them")
    var selectedPrimaryPronoun by remember{
        mutableStateOf("")
    }
    var selectedSecondaryPronoun by remember{
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.LUKY),
            fontSize = 25.sp,
            fontFamily = Raleway,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.CHOOSE_YOUR_GENDER),
            modifier = Modifier.fillMaxWidth(0.8f),
            fontFamily = Raleway,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(genderList) { gender ->
                InputChip(
                    selected = (selectedGender == gender),
                    onClick = {
                        selectedGender = gender
                    },
                    label = {
                        Text(
                            text = gender,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    avatar = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null)
                    },
                    colors = InputChipDefaults.inputChipColors(
                        containerColor = Color.White,
                        labelColor = themeBlack,
                        leadingIconColor = themeBlack,
                        selectedContainerColor = themeRed,
                        selectedLabelColor = Color.White,
                        selectedLeadingIconColor = Color.White,
                        selectedTrailingIconColor = Color.White
                    ),
                    shape = CircleShape,
                    trailingIcon = {
                        if (selectedGender == gender) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .padding(
                            horizontal = 5.dp
                        )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.CHOOSE_PRONOUNS),
            fontFamily = Raleway,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.PRIMARY_PRONOUN),
            fontFamily = Raleway,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(primaryPronounList) { primaryPronoun ->
                InputChip(
                    selected = (selectedPrimaryPronoun == primaryPronoun),
                    onClick = {
                        selectedPrimaryPronoun = primaryPronoun
                    },
                    label = {
                        Text(
                            text = primaryPronoun,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    avatar = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null)
                    },
                    colors = InputChipDefaults.inputChipColors(
                        containerColor = Color.White,
                        labelColor = themeBlack,
                        leadingIconColor = themeBlack,
                        selectedContainerColor = themeRed,
                        selectedLabelColor = Color.White,
                        selectedLeadingIconColor = Color.White,
                        selectedTrailingIconColor = Color.White
                    ),
                    shape = CircleShape,
                    trailingIcon = {
                        if (selectedPrimaryPronoun == primaryPronoun) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .padding(
                            horizontal = 5.dp
                        )
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.SECONDARY_PRONOUN),
            fontFamily = Raleway,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(secondaryPronounList) { secondaryPronoun ->
                InputChip(
                    selected = (selectedSecondaryPronoun == secondaryPronoun),
                    onClick = {
                        selectedSecondaryPronoun = secondaryPronoun
                    },
                    label = {
                        Text(
                            text = secondaryPronoun,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    avatar = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null)
                    },
                    colors = InputChipDefaults.inputChipColors(
                        containerColor = Color.White,
                        labelColor = themeBlack,
                        leadingIconColor = themeBlack,
                        selectedContainerColor = themeRed,
                        selectedLabelColor = Color.White,
                        selectedLeadingIconColor = Color.White,
                        selectedTrailingIconColor = Color.White
                    ),
                    shape = CircleShape,
                    trailingIcon = {
                        if (selectedSecondaryPronoun == secondaryPronoun) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .padding(
                            horizontal = 5.dp
                        )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Your Pronouns: ${selectedPrimaryPronoun}/${selectedSecondaryPronoun}",
            fontWeight = FontWeight.SemiBold,
            fontFamily = Raleway,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(40.dp))
        ElevatedButton(
            onClick = {
                viewModel?.register(age = age, gender = selectedGender, selectedPrimaryPronoun, selectedSecondaryPronoun)
            },
            elevation = ButtonDefaults.elevatedButtonElevation(3.dp),
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
                fontFamily = Raleway,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = null
            )
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


@Preview
@Composable
fun picker() {
    RegistrationScreen(viewModel = null, navController = null)
}