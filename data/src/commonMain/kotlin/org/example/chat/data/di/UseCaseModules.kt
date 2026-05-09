package org.example.chat.data.di

import org.example.chat.domain.usecase.message.ObserveMessagesUseCase
import org.example.chat.domain.usecase.message.SendMessageUseCase
import org.example.chat.domain.usecase.user.GetOtherUserUseCase
import org.example.chat.domain.usecase.user.ObserveCurrentUserUseCase
import org.example.chat.domain.usecase.user.SwitchUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModules = module {
    factoryOf(::GetOtherUserUseCase)
    factoryOf(::ObserveCurrentUserUseCase)
    factoryOf(::ObserveMessagesUseCase)
    factoryOf(::SendMessageUseCase)
    factoryOf(::SwitchUserUseCase)
}
