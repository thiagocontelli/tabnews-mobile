package com.thiagocontelli.tabnewsmobile.data.dtos

data class GetPostContentDto(
    val body: String,
    val children_deep_count: Int,
    val created_at: String,
    val deleted_at: Any,
    val id: String,
    val owner_id: String,
    val owner_username: String,
    val parent_id: Any,
    val published_at: String,
    val slug: String,
    val source_url: Any,
    val status: String,
    val tabcoins: Int,
    val title: String,
    val updated_at: String
)