package com.eunoia.app.user.registration.user_interface

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.eunoia.app.R
import com.eunoia.app.common.LoadingScreen.LoadingScreen
import com.eunoia.app.navigation.Routes
import com.eunoia.app.ui.theme.Raleway
import com.eunoia.app.ui.theme.themeBlack
import com.eunoia.app.ui.theme.themeWhite
import com.eunoia.app.user.registration.user_interface.viewmodel.ProfilePhotoViewModel
import com.eunoia.app.utils.Response

@Composable
fun PickProfilePhoto(viewModel: ProfilePhotoViewModel?, navController: NavHostController?) {

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri }
    )
    val photoFlow = viewModel?.pictureFlow?.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.CHOOSE_PROFILE_PHOTO),
            fontFamily = Raleway,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .background(Color.Black)
                .width(200.dp)
                .height(200.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            AsyncImage(
                model = selectedImageUri,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        ElevatedButton(
            onClick = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
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
                text = stringResource(id = R.string.PICK_PHOTO),
                fontFamily = Raleway,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        ElevatedButton(
            onClick = {
                viewModel!!.updateProfilePhoto(selectedImageUri!!)
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
                text = stringResource(id = R.string.PROCEED),
                fontFamily = Raleway,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
        }
    }
    photoFlow?.value?.let {
        when (it) {
            is Response.Error -> {
                val context = LocalContext.current
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            }

            Response.Loading -> {
                LoadingScreen()
            }

            is Response.Success -> {
                val context = LocalContext.current
                Toast.makeText(context, "Registered Successfully!!", Toast.LENGTH_SHORT).show()
                LaunchedEffect(Unit) {
                    navController?.navigate(Routes.Home.route) {
                        popUpTo(Routes.PickProfilePhoto.route) { inclusive = true }
                    }
                }
            }
        }
    }
}