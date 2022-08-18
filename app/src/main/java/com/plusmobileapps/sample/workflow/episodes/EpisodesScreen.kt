package com.plusmobileapps.sample.workflow.episodes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.compose.ComposeScreen

data class EpisodesScreen(
    val episodes: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val onEpisodeClicked: (String) -> Unit = {}
) : ComposeScreen {
    @Composable
    override fun Content(viewEnvironment: ViewEnvironment) {
        LazyColumn {
            items(episodes) {
                Text(it, modifier = Modifier.clickable { onEpisodeClicked(it) })
            }
        }
    }
}


