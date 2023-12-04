package com.eunoia.app.user.registration.user_interface.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eunoia.app.R
import com.eunoia.app.ui.theme.Raleway
import com.eunoia.app.ui.theme.themeWhite

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    name: String,
    primaryPronoun: String,
    secondaryPronoun: String,
    location: String,
    image: Painter
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = modifier
                .fillMaxWidth(0.85f)
                .height(400.dp)
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    TextDisplay(
                        modifier = modifier,
                        name,
                        primaryPronoun,
                        secondaryPronoun,
                        location
                    )
                }
            }
        }
    }
}

@Composable
fun NameDisplay(
    modifier: Modifier,
    name: String,
    primaryPronoun: String,
    secondaryPronoun: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = name,
            fontFamily = Raleway,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = "${primaryPronoun}/${secondaryPronoun}",
            fontFamily = Raleway,
            fontWeight = FontWeight.Normal,
            color = themeWhite,
            fontSize = 12.sp
        )
    }
}

@Composable
fun TextDisplay(
    modifier: Modifier,
    name: String,
    primaryPronoun: String,
    secondaryPronoun: String,
    location: String
) {
    val startColor = Color.Black.copy(alpha = 0f)
    val centerColor = Color.Black.copy(alpha = 0.8f)
    val endColor = Color.Black.copy(alpha = 1f)
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(startColor, centerColor, endColor),
    )
    Box(
        modifier = modifier
            .fillMaxHeight(0.6f)
            .background(gradientBrush),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column {
            NameDisplay(modifier = modifier, name, primaryPronoun, secondaryPronoun)
            Spacer(modifier = Modifier.height(4.dp))
            LocationDisiplay(modifier, location)
            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}

@Composable
fun LocationDisiplay(modifier: Modifier, location: String) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = Icons.Outlined.LocationOn,
            contentDescription = null,
            tint = themeWhite,
            modifier = Modifier.size(16.dp)
        )
//        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = location,
            fontFamily = Raleway,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 14.sp
        )
    }
}
@Preview
@Composable
fun UserCardPreview() {
    UserCard(Modifier, "Pratyush", "He", "Him", "Bangalore", painterResource(id = R.drawable.test))
}