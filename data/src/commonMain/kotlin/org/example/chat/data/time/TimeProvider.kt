package org.example.chat.data.time

import kotlin.time.Clock

object TimeProvider {
    fun currentTimeMillis(): Long = Clock.System.now().toEpochMilliseconds()
}