package org.example.chat.data.datasource.db

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.CoroutineDispatcher

fun createDatabase(
    factory: DatabaseFactory,
    ioDispatcher: CoroutineDispatcher
): AppDatabase =
    factory.createBuilder()
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(ioDispatcher)
        .build()
