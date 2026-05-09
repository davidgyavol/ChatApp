package org.example.chat.data.mapper

import org.example.chat.data.model.message.MessageEntity
import org.example.chat.domain.model.message.Message

fun MessageEntity.toMessage() = Message(
    id = id,
    text = text,
    senderUserId = senderUserId,
    sentAt = sentAt
)