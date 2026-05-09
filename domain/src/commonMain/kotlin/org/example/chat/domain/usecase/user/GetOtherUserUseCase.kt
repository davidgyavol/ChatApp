package org.example.chat.domain.usecase.user

import org.example.chat.domain.repository.user.UserRepository

class GetOtherUserUseCase(
    private val userRepository: UserRepository
) {

    operator fun invoke() = userRepository.otherUser
}