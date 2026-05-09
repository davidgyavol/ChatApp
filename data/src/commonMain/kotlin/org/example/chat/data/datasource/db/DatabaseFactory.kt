package org.example.chat.data.datasource.db

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun createBuilder(): RoomDatabase.Builder<AppDatabase>
}