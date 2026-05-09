package org.example.chat.data.datasource.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DB_NAME = "chat.db"

actual class DatabaseFactory(private val context: Context) {
    actual fun createBuilder(): RoomDatabase.Builder<AppDatabase> =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME
        )
}