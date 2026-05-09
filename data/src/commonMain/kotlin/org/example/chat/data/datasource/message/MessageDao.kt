package org.example.chat.data.datasource.message

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.example.chat.data.model.message.MessageEntity

@Dao
interface MessageDao {

    @Insert
    suspend fun insert(message: MessageEntity)

    @Query(
        """
    SELECT * FROM messages 
    WHERE (senderUserId = :currentUserId AND otherUserId = :otherUserId)
    OR (senderUserId = :otherUserId AND otherUserId = :currentUserId)
    ORDER BY sentAt ASC
"""
    )
    fun observeAllMessages(currentUserId: String, otherUserId: String): Flow<List<MessageEntity>>
}