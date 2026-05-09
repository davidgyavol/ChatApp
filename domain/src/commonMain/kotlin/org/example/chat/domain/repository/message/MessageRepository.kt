package org.example.chat.domain.repository.message

import kotlinx.coroutines.flow.Flow
import org.example.chat.domain.model.message.Message

interface MessageRepository {

    fun observeMessages(currentUserId: String, otherUserId: String): Flow<List<Message>>

    suspend fun sendMessage(
        otherUserId: String,
        message: String
    )
}