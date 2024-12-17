package com.example.pixabayviewer.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pixabayviewer.core.model.PixabayImage
import com.example.pixabayviewer.core.network.PixabayApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val pixabayApi: PixabayApi
) : ViewModel() {
    private val _isGridView = MutableStateFlow(true)
    val isGridView: StateFlow<Boolean> = _isGridView

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun toggleViewMode() {
        _isGridView.value = !_isGridView.value
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchImages(query: String): Flow<PagingData<PixabayImage>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PixabayPagingSource(pixabayApi, query)
            }
        ).flow.cachedIn(viewModelScope)
    }
}