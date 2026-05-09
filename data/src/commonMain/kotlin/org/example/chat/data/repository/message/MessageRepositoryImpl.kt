package org.example.chat.data.repository.message

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.example.chat.data.datasource.message.MessageDao
import org.example.chat.data.di.IoDispatcher
import org.example.chat.data.mapper.toMessage
import org.example.chat.data.model.message.MessageEntity
import org.example.chat.data.time.TimeProvider
import org.example.chat.domain.repository.message.MessageRepository
import org.example.chat.domain.repository.user.UserRepository

class MessageRepositoryImpl(
    private val messageDao: MessageDao,
    private val userRepository: UserRepository,
    @param:IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : MessageRepository {

    override fun observeMessages(currentUserId: String, otherUserId: String) =
        messageDao.observeAllMessages(currentUserId, otherUserId)
            .map { entities ->
                entities.map { it.toMessage() }
            }
            .flowOn(ioDispatcher)

    override suspend fun sendMessage(
        otherUserId: String,
        message: String
    ) = withContext(ioDispatcher) {
        messageDao.insert(
            MessageEntity(
                senderUserId = userRepository.currentUser.value.id,
                otherUserId = otherUserId,
                text = message,
                sentAt = TimeProvider.currentTimeMillis()
            )
        )
    }
}