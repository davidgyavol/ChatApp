package org.example.chat.domain.usecase.user

import org.example.chat.domain.repository.user.UserRepository

class ObserveCurrentUserUseCase(
    private val userRepository: UserRepository
) {

    operator fun invoke() = userRepository.currentUser
}