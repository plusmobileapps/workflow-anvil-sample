package com.plusmobileapps.sample.workflow.characters.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.squareup.workflow1.ui.Screen
import com.squareup.workflow1.ui.compose.composeScreenViewFactory

data class CharacterSearchScreen(
    val isLoading: Boolean,
    val name: String = "",
    val onBack: () -> Unit,
) : Screen

val CharacterSearchBinding =
    composeScreenViewFactory<CharacterSearchScreen> { rendering, environment ->
        Scaffold(
            topBar = {
                TopAppBar(navigationIcon = {
                    IconButton(onClick = rendering.onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }, title = {})
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                if (rendering.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(text = rendering.name)
                }
            }
        }
    }
