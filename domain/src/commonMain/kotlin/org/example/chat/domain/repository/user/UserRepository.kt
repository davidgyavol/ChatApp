package org.example.chat.domain.repository.user

import kotlinx.coroutines.flow.StateFlow
import org.example.chat.domain.model.user.User

interface UserRepository {

    val currentUser: StateFlow<User>

    val otherUser: User

    suspend fun switchUser()
}