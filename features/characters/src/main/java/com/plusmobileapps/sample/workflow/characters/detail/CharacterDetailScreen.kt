package com.plusmobileapps.sample.workflow.characters.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.squareup.workflow1.ui.Screen
import com.squareup.workflow1.ui.compose.composeScreenViewFactory

data class CharacterDetailScreen(
    val characterId: Int,
    val name: String,
    val onBackClick: () -> Unit,
) : Screen {
    companion object {
        val viewFactory =
            composeScreenViewFactory<CharacterDetailScreen> { rendering, environment ->
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Character Detail") },
                            navigationIcon = {
                                IconButton(onClick = rendering.onBackClick) {
                                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                                }
                            }
                        )
                    }
                ) {
                    Column(modifier = Modifier.padding(it)) {
                        Text(text = rendering.name)
                        Text(text = "Character id: ${rendering.characterId}")
                    }
                }
            }
    }
}
