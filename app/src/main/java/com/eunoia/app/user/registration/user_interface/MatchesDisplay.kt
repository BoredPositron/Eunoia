package com.eunoia.app.user.registration.user_interface

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import com.eunoia.app.user.registration.data.model.User
import com.eunoia.app.user.registration.user_interface.components.UserCard
import com.eunoia.app.user.registration.user_interface.viewmodel.HomeViewModel


@Composable
fun Matches(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
){
    val dataState = viewModel.dataFlow.collectAsState()
    val users: List<User>? = dataState.value
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ){
            items(items = users!!){user->
                UserCard(
                    name = user.name,
                    primaryPronoun = user.primaryPronoun,
                    secondaryPronoun = user.secondaryPronoun,
                    location = "Bangalore",
                    image = rememberImagePainter(
                        data = user.profilePhotoLink,
                        builder = {
                            crossfade(false)
                        }
                    )
                )
            }
        }
    }
    
}

@Composable
fun UserDisplay(user: User) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ){
        Text(text = user.name)
    }
}
