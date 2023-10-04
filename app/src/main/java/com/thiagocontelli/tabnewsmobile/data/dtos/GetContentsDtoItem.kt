package com.thiagocontelli.tabnewsmobile.data.dtos

import com.thiagocontelli.tabnewsmobile.models.Post

data class GetContentsDtoItem(
    val children_deep_count: Int,
    val created_at: String,
    val id: String,
    val owner_id: String,
    val owner_username: String,
    val published_at: String,
    val slug: String,
    val source_url: String,
    val status: String,
    val tabcoins: Int,
    val title: String,
    val updated_at: String
)

fun GetContentsDtoItem.toModel(): Post {
    return Post(
        id = this.id,
        title = this.title,
        tabcoins = this.tabcoins,
        commentsAmount = this.children_deep_count,
        username = this.owner_username,
        slug = this.slug
    )
}