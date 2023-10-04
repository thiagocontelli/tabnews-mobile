package com.thiagocontelli.tabnewsmobile.features.post

import androidx.lifecycle.ViewModel
import com.thiagocontelli.tabnewsmobile.data.TabNewsApi
import com.thiagocontelli.tabnewsmobile.data.dtos.GetPostContentDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val api: TabNewsApi) : ViewModel() {

    fun getPostContent(user: String, slug: String): Flow<Result<GetPostContentDto>> = flow {
        try {
            val dto = api.getPostContent(user = user, slug = slug)
            emit(Result.success(dto))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}