package com.plusmobileapps.sample.workflow.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.squareup.workflow1.ui.Screen
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.compose.ComposeScreen
import com.squareup.workflow1.ui.compose.composeScreenViewFactory

data class CharactersScreen(
    val characters: List<RickAndMortyCharacter> = emptyList(),
    val isLoading: Boolean = false,
    val onCharacterClicked: (RickAndMortyCharacter) -> Unit = {},
    val goToEpisodeClicked: () -> Unit
) : Screen

val CharactersBinding = composeScreenViewFactory<CharactersScreen> { rendering, environment ->
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Episodes") },
                actions = {
                    IconButton(onClick = { rendering.goToEpisodeClicked() }) {
                        Icon(Icons.Default.Person, contentDescription = "Go to characters")
                    }
                },
            )
        },
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(rendering.characters, key = { it.id }) {
                Text(text = it.name, modifier = Modifier.clickable { rendering.onCharacterClicked(it) })
            }
        }
    }
}
