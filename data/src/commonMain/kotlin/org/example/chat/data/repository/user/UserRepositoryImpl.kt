package org.example.chat.data.repository.user

import kotlinx.coroutines.flow.StateFlow
import org.example.chat.data.datasource.user.InMemoryUserDataSource
import org.example.chat.domain.model.user.User
import org.example.chat.domain.repository.user.UserRepository

class UserRepositoryImpl(
    private val inMemoryUserDataSource: InMemoryUserDataSource
) : UserRepository {

    override val currentUser: StateFlow<User> = inMemoryUserDataSource.currentUser
    override val otherUser: User
        get() = inMemoryUserDataSource.otherUser

    override suspend fun switchUser() {
        val current = inMemoryUserDataSource.currentUser.value
        val next = inMemoryUserDataSource.getUsers().first { it.id != current.id }
        inMemoryUserDataSource.setCurrentUser(next)
    }
}