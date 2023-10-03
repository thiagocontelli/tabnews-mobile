package com.thiagocontelli.tabnewsmobile.features.home

import androidx.lifecycle.ViewModel
import com.thiagocontelli.tabnewsmobile.data.TabNewsApi
import com.thiagocontelli.tabnewsmobile.data.dtos.toModel
import com.thiagocontelli.tabnewsmobile.models.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecentsViewModel @Inject constructor(private val api: TabNewsApi) : ViewModel() {
    private var _state = MutableStateFlow(State())
    private val state: StateFlow<State> = _state

    fun getPosts(): Flow<Result<List<Post>>> = flow {
        try {
            val dto = api.getContents(page = state.value.nextPage, strategy = "new")
            val posts = dto.map { it.toModel() }
            _state.update { it.copy(nextPage = it.nextPage + 1) }
            emit(Result.success(posts))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}