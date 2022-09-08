package com.plusmobileapps.sample.workflow.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.plusmobileapps.rickandmortysdk.characters.RickAndMortyCharacter
import com.squareup.workflow1.ui.Screen
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
                title = { Text(text = "Characters") },
                actions = {
                    IconButton(onClick = { rendering.goToEpisodeClicked() }) {
                        Icon(Icons.Default.Person, contentDescription = "Go to characters")
                    }
                },
            )
        },
    ) { paddingValues ->
        if (rendering.isLoading) {
            CircularProgressIndicator()
        }
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(rendering.characters, key = { it.id }) {
                Text(text = it.name, modifier = Modifier.clickable { rendering.onCharacterClicked(it) })
            }
        }
    }
}
