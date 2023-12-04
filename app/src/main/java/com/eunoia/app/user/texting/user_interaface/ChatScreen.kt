package com.eunoia.app.user.texting.user_interaface

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.twotone.Call
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eunoia.app.R
import com.eunoia.app.ui.theme.Raleway
import com.eunoia.app.user.texting.data.model.ChatMessage

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    var userText by remember {
        mutableStateOf("")
    }
    val chats = listOf(
        ChatMessage("A", "Hello World How are you?", "", null, "A"),
        ChatMessage("B", "Hello World", "", null, "A")
    )
    Scaffold(
        topBar = { TopBar(modifier = modifier) },
        bottomBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = userText,
                    onValueChange = {
                        userText = it
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = null,
                            modifier = Modifier.clickable {

                            }
                        )
                    },
                    modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(text = stringResource(id = R.string.ENTER_TXT))
                    }
                )
            }
        }
    ) {
        Column(
            modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ChatLyt(modifier = modifier.padding(it), chats = chats, myuid = "A")
        }

    }
}

@Composable
fun ChatLyt(modifier: Modifier, chats: List<ChatMessage>, myuid: String) {
    LazyColumn(
        modifier = modifier.fillMaxHeight()
    ) {
        items(chats) { message ->
            Message(text = message, myuid = myuid)
        }
    }
}

@Composable
fun TopBar(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ImageCard()
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = "BoredPositron",
            fontFamily = Raleway,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.width(20.dp))
        Icon(
            imageVector = Icons.TwoTone.Call, contentDescription = null
        )
    }
}


@Composable
fun SentMessage(text: String) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(
            topStart = 10.dp,
            bottomStart = 10.dp,
            bottomEnd = 2.dp,
            topEnd = 10.dp
        ),
        colors = CardDefaults.cardColors(

        ),

        ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(10.dp),
            fontFamily = Raleway,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Right,
            fontSize = 12.sp
        )
    }
}


@Composable
fun RecMessage(text: String) {
    Card(
        modifier = Modifier
            .wrapContentSize(),
        shape = RoundedCornerShape(
            topStart = 10.dp,
            bottomStart = 2.dp,
            bottomEnd = 10.dp,
            topEnd = 10.dp
        ),
        colors = CardDefaults.cardColors(

        )
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(10.dp),
            fontFamily = Raleway,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
    }
}

@Composable
fun Message(text: ChatMessage, myuid: String) {
    if (myuid == text.senderUid) {
        Row(
            Modifier.fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.End
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            SentMessage(text = text.message)
        }
    } else {
        Row(
            Modifier.fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.Start
        ) {
            RecMessage(text = text.message)
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}

@Composable
fun ImageCard(
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(
            modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.test),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}