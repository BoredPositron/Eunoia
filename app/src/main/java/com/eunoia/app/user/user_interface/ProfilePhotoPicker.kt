package com.eunoia.app.user.user_interface

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.eunoia.app.navigation.Routes
import com.eunoia.app.user.user_interface.viewmodel.ProfilePhotoViewModel
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
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .background(Color.Black)
                .width(200.dp)
                .height(200.dp),
            shape = CircleShape
        ) {
            AsyncImage(
                model = selectedImageUri,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Button(
            onClick = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            },
        ) {
            Text(text = "Pick Photo")
        }
        Button(
            onClick = {
                viewModel!!.updateProfilePhoto(selectedImageUri!!)
                      },
        ) {
            Text(text = "Select Photo")
        }
    }
        photoFlow?.value?.let {
        when (it) {
            is Response.Error -> {
                val context = LocalContext.current
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

            Response.Loading -> {
                CircularProgressIndicator()
            }

            is Response.Success -> {
                println(selectedImageUri)
                LaunchedEffect(Unit) {
                    navController?.navigate(Routes.Home.route) {
                        popUpTo(Routes.PickProfilePhoto.route) { inclusive = true }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun preview() {
    PickProfilePhoto(null, null)
}