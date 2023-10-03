package com.thiagocontelli.tabnewsmobile

import java.time.LocalDateTime

data class Post(
    val id: String,
    val title: String,
    val tabCoins: Int,
    val ownerUsername: String,
    val publishedAt: LocalDateTime,
    val commentsAmount: Int
)
