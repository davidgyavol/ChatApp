package org.example.chat.data.di

import org.example.chat.data.datasource.db.AppDatabase
import org.example.chat.data.datasource.db.createDatabase
import org.example.chat.data.datasource.message.MessageDao
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        createDatabase(get(), get(qualifier<IoDispatcher>()))
    }

    single<MessageDao> {
        get<AppDatabase>().messageDao()
    }
}