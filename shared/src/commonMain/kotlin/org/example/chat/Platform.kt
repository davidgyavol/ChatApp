package org.example.chat

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform