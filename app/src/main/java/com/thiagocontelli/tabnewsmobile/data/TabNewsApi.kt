package com.thiagocontelli.tabnewsmobile.data

import com.thiagocontelli.tabnewsmobile.data.dtos.GetContentsDto
import com.thiagocontelli.tabnewsmobile.data.dtos.GetPostContentDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TabNewsApi {
    @GET("contents")
    suspend fun getContents(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30,
        @Query("strategy") strategy: String = "relevant"
    ): GetContentsDto

    @GET("contents/{user}/{slug}")
    suspend fun getPostContent(
        @Path("user") user: String,
        @Path("slug") slug: String
    ): GetPostContentDto
}