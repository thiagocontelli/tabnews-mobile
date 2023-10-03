package com.thiagocontelli.tabnewsmobile.data

import com.thiagocontelli.tabnewsmobile.data.dtos.GetContentsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TabNewsApi {
    @GET("contents")
    suspend fun getContents(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30,
        @Query("strategy") strategy: String = "relevant"
    ): GetContentsDto
}