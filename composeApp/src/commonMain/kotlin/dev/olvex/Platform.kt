package dev.olvex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform