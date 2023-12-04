package com.eunoia.app.user.texting.domain

import com.eunoia.app.utils.Response
import java.sql.Timestamp

interface ChatRepository {
    suspend fun  sendMessage(senderUid: String, recieverUid: String, message: String, timestamp: Timestamp): Response<Boolean>
}