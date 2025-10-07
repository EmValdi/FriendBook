package org.junia.friendbook

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform