package org.example.chat.domain.usecase.message

import org.example.chat.domain.repository.message.MessageRepository

class SendMessageUseCase(
    private val messageRepository: MessageRepository
) {

    suspend operator fun invoke(otherUserId: String, message: String) {
        messageRepository.sendMessage(otherUserId, message)
    }
}