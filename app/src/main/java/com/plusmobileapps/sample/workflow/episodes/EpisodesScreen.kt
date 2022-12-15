package com.plusmobileapps.sample.workflow.episodes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.squareup.workflow1.ui.Screen
import com.squareup.workflow1.ui.compose.composeScreenViewFactory

data class EpisodesScreen(
    val episodes: List<Episode> = emptyList(),
    val isLoading: Boolean = false,
    val onEpisodeClicked: (Episode) -> Unit = {},
    val onBackClicked: () -> Unit,
) : Screen

val EpisodesBinding = composeScreenViewFactory<EpisodesScreen> { rendering, environment ->
    EpisodesListView(rendering = rendering)
}

@Composable
fun EpisodesListView(
    modifier: Modifier = Modifier,
    rendering: EpisodesScreen,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Episodes") },
                navigationIcon = {
                    IconButton(onClick = { rendering.onBackClicked() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(rendering.episodes) { item ->
                Text(item.name, modifier = Modifier.clickable { rendering.onEpisodeClicked(item) })
            }
        }
    }
}


