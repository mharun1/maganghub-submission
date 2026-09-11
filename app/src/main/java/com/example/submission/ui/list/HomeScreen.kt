package com.example.submission.ui.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.submission.data.model.Show
import com.example.submission.ui.common.UiState
import com.example.submission.ui.component.ShowItem
import com.example.submission.ui.theme.SubmissionTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = viewModel(),
    onItemClick: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TV Maze") },
                modifier = Modifier.shadow(2.dp)
            )
        },
        modifier = modifier
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        when (val state = uiState) {
            is UiState.Loading -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(innerPadding).fillMaxSize()
                ) {
                    ContainedLoadingIndicator()
                }
            }
            is UiState.Success -> {
                HomeContent(
                    shows = state.data,
                    onItemClick = onItemClick,
                    modifier = Modifier.padding(innerPadding)
                )
            }
            is UiState.Error -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(24.dp)
                        .fillMaxSize()
                ) {
                    Text(text = state.errorMessage, textAlign = TextAlign.Center)
                    Button(onClick = {viewModel.getShows()}) { Text("Retry") }
                }
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
        contentPadding = PaddingValues(24.dp),
        modifier = modifier.fillMaxSize()
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    SubmissionTheme {
        HomeScreen(onItemClick = {})
    }
}