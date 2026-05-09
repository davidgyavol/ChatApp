package org.example.chat.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.example.chat.data.datasource.message.MessageDao
import org.example.chat.data.model.message.MessageEntity

@Database(
    entities = [MessageEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}