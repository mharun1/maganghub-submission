package com.example.submission.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.submission.data.model.Show
import com.example.submission.ui.common.UiState
import com.example.submission.ui.component.ShowItem
import com.example.submission.ui.theme.SubmissionTheme
import com.google.android.material.loadingindicator.LoadingIndicator

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = viewModel(),
    onItemClick: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is UiState.Loading -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxSize()
            ) {
                ContainedLoadingIndicator()
            }
        }
        is UiState.Success -> {
            HomeContent(
                shows = state.data,
                onItemClick = onItemClick
            )
        }
        is UiState.Error -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.fillMaxSize()
            ) {
                Text(text = state.errorMessage)
            }
        }
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    shows: List<Show>,
    onItemClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        items(shows, key = { it.id }) { show ->
            ShowItem(
                poster = show.listPoster,
                title = show.title,
                rating = show.rating,
                modifier = Modifier.clickable { onItemClick(show.id) }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    SubmissionTheme {
        HomeScreen(onItemClick = {})
    }
}