package com.example.submission.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.example.submission.ui.common.UiState
import com.example.submission.ui.theme.SubmissionTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(),
    id: Int,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(id) {
        viewModel.getDetailShow(id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("TV Maze", maxLines = 1, overflow = TextOverflow.Ellipsis)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Navigation back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (uiState is UiState.Success) {
                            val show = (uiState as UiState.Success).data
                            shareShow(
                                context = context,
                                title = show.title,
                                summary = show.summary,
                                url = show.url
                            )
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share button",
                        )
                    }
                },
                modifier = Modifier.shadow(2.dp)
            )
        },
        modifier = modifier
    ) { innerPadding ->
        when (val state = uiState) {
            is UiState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize().padding(innerPadding)
                ) {
                    ContainedLoadingIndicator()
                }
            }
            is UiState.Success -> {
                val show = state.data
                DetailContent(
                    title = show.title,
                    poster = show.detailPoster,
                    summary = show.summary,
                    rating = show.rating,
                    premiered = show.premiered,
                    modifier = Modifier.padding(innerPadding)
                )
            }
            is UiState.Error -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier.fillMaxSize().padding(innerPadding)
                ) {
                    Text(text = state.errorMessage)
                }
            }
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    poster: String,
    title: String,
    rating: Double?,
    summary: String,
    premiered: String,
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        SubcomposeAsyncImage(
            model = poster,
            contentDescription = "$title Poster",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(480.dp)
                .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
        )
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(24.dp))
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFC107)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = rating.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = "Premiered on $premiered"
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Summary",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = summary,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
            )
        }
    }
}

fun shareShow(
    context: Context,
    title: String,
    summary: String,
    url: String,
) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, title)
        putExtra(Intent.EXTRA_TEXT, "$title\n\n$summary\n\n$url")
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share"))
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    SubmissionTheme {
        DetailScreen(id = 1, onBack = {})
    }
}