package org.example.chat.data.datasource.user

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.chat.domain.model.user.User

class InMemoryUserDataSource {

    private val users = listOf(
        User(id = "1", name = "John"),
        User(id = "2", name = "Sarah")
    )

    private val _currentUser = MutableStateFlow(users[0])
    val currentUser: StateFlow<User> = _currentUser.asStateFlow()

    val otherUser: User
        get() = users.first { it.id != currentUser.value.id }

    fun getUsers(): List<User> = users

    fun setCurrentUser(user: User) {
        _currentUser.value = user
    }
}