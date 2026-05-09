package org.example.chat.data.di

import org.example.chat.data.repository.message.MessageRepositoryImpl
import org.example.chat.data.repository.user.UserRepositoryImpl
import org.example.chat.domain.repository.message.MessageRepository
import org.example.chat.domain.repository.user.UserRepository
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val repositoryModules = module {
    single<MessageRepository> {
        MessageRepositoryImpl(
            messageDao = get(),
            userRepository = get(),
            ioDispatcher = get(qualifier<IoDispatcher>())
        )
    }
    single<UserRepository> {
        UserRepositoryImpl(
            inMemoryUserDataSource = get()
        )
    }
}
