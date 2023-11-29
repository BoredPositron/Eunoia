package com.eunoia.app.user.texting.data.model

data class ChatMessage (
    val senderUid: String,
    val message: String,
    val images: String,
    val seen: Boolean? = null,
    val timeStamp: String
)