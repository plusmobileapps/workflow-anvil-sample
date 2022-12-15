package com.plusmobileapps.sample.workflow.characters

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.squareup.workflow1.ui.Screen
import com.squareup.workflow1.ui.compose.composeScreenViewFactory

data class CharactersScreen(
    val characters: List<RickAndMortyCharacter> = emptyList(),
    val isLoading: Boolean = false,
    val onCharacterClicked: (RickAndMortyCharacter) -> Unit = {},
    val onLoadMoreClicked: () -> Unit
) : Screen

val CharactersBinding = composeScreenViewFactory<CharactersScreen> { rendering, environment ->
    CharactersListView(rendering = rendering)
}

@Composable
fun CharactersListView(modifier: Modifier = Modifier, rendering: CharactersScreen) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Characters") },
            )
        },
    ) { paddingValues ->
        if (rendering.isLoading) {
            CircularProgressIndicator()
        }
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(rendering.characters, key = { it.id }) {
                Text(
                    text = it.name,
                    modifier = Modifier.clickable { rendering.onCharacterClicked(it) })
            }

            item("characters-loading-page") {
                AnimatedVisibility(visible = rendering.isLoading) {
                    CircularProgressIndicator()
                }
            }
            item("characters-load-more") {
                AnimatedVisibility(visible = !rendering.isLoading) {
                    Button(onClick = rendering.onLoadMoreClicked) {
                        Text("Load More")
                    }
                }
            }
        }
    }
}
