package com.thiagocontelli.tabnewsmobile.models

data class Post(
    val id: String,
    val title: String,
    val tabcoins: Int,
    val username: String,
    val commentsAmount: Int,
    val slug: String
)
