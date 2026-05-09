package org.example.chat.domain.usecase.message

import org.example.chat.domain.repository.message.MessageRepository
import org.example.chat.domain.repository.user.UserRepository

class ObserveMessagesUseCase(
    private val messageRepository: MessageRepository,
    private val userRepository: UserRepository
) {

    operator fun invoke(otherUserId: String) =
        messageRepository.observeMessages(
            currentUserId = userRepository.currentUser.value.id,
            otherUserId = otherUserId
        )
}