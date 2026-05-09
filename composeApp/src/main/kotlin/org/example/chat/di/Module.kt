package org.example.chat.di

import org.example.chat.message.MessageViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModules = module {
    viewModelOf(::MessageViewModel)
}