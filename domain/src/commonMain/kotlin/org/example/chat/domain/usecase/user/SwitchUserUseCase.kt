package org.example.chat.domain.usecase.user

import org.example.chat.domain.repository.user.UserRepository

class SwitchUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() {
        userRepository.switchUser()
    }
}