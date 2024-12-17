package com.example.pixabayviewer.feature.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onImageClick: (Long) -> Unit
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isGridView by viewModel.isGridView.collectAsState()
    val images = viewModel.searchImages(searchQuery).collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchBar(
                query = searchQuery,
                onQueryChange = viewModel::updateSearchQuery
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.toggleViewMode() }
            ) {
                // Toggle icon
            }
        }
    ) { padding ->
        if (isGridView) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = padding,
                modifier = Modifier.fillMaxSize()
            ) {
                items(images.itemCount) { index ->
                    images[index]?.let { image ->
                        ImageGridItem(
                            image = image,
                            onClick = { onImageClick(image.id) }
                        )
                    }
                }
            }
        } else {
            LazyColumn(
                contentPadding = padding,
                modifier = Modifier.fillMaxSize()
            ) {
                items(images.itemCount) { index ->
                    images[index]?.let { image ->
                        ImageListItem(
                            image = image,
                            onClick = { onImageClick(image.id) }
                        )
                    }
                }
            }
        }
    }
}