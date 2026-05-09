package org.example.chat.data.model.message

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val senderUserId: String,
    val otherUserId: String,
    val text: String,
    val sentAt: Long
)