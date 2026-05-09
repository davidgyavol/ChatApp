package org.example.chat.domain.model.message

data class Message(
    val id: Long,
    val text: String,
    val senderUserId: String,
    val sentAt: Long
)