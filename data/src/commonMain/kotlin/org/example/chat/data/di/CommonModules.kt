package org.example.chat.data.di

import org.example.chat.data.datasource.user.InMemoryUserDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {
    singleOf(::InMemoryUserDataSource)
}