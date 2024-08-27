package com.fadhlansulistiyo.cinemacatalog.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Movie
import com.fadhlansulistiyo.cinemacatalog.ui.screen.home.HomeViewModel

@Composable
fun MovieListScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetails: (Int) -> Unit
) {
    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(nowPlayingMovies.itemCount) { index ->
            val movie = nowPlayingMovies[index]
            movie?.let {
                MovieItem(movie = it, onClick = { navigateToDetails(movie.id) })
            }
        }

        // Handling different loading states
        when {
            nowPlayingMovies.loadState.refresh is LoadState.Loading -> {
                item { LoadingItem() }
            }
            nowPlayingMovies.loadState.append is LoadState.Loading -> {
                item { LoadingItem() }
            }
            nowPlayingMovies.loadState.refresh is LoadState.Error -> {
                Log.d("MovieListScreen", "Error: ${nowPlayingMovies.loadState.refresh as LoadState.Error}")
                val error = nowPlayingMovies.loadState.refresh as LoadState.Error
                item {
                    ErrorItem(
                        message = error.error.localizedMessage ?: "Unknown error",
                        onRetry = { nowPlayingMovies.retry() }
                    )
                }
            }
            nowPlayingMovies.loadState.append is LoadState.Error -> {
                Log.d("MovieListScreen", "Error: ${nowPlayingMovies.loadState.append as LoadState.Error}")
                val error = nowPlayingMovies.loadState.append as LoadState.Error
                item {
                    ErrorItem(
                        message = error.error.localizedMessage ?: "Unknown error",
                        onRetry = { nowPlayingMovies.retry() }
                    )
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${movie.posterPath}"),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
            Text(text = "Release Date: ${movie.releaseDate}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Rating: ${movie.voteAverage}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorItem(message: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = message, color = Color.Red, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetry) {
                Text("Retry")
            }
        }
    }
}