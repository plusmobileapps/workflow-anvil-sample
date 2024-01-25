package com.plusmobileapps.sample.workflow.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.squareup.workflow1.ui.Screen
import com.squareup.workflow1.ui.ScreenViewFactory
import com.squareup.workflow1.ui.compose.composeScreenViewFactory

data class CharacterDetailScreen(
    val characterId: Int,
    val name: String,
) : Screen {
    companion object {
        val viewFactory = composeScreenViewFactory<CharacterDetailScreen> { rendering, environment ->
            Scaffold {
                Column(modifier = Modifier.padding(it)) {
                    Text(text = rendering.name)
                }
            }
        }
    }
}
