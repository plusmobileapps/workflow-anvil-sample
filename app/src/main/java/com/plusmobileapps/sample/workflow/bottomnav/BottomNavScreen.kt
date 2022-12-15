package com.plusmobileapps.sample.workflow.bottomnav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.plusmobileapps.sample.workflow.characters.CharactersListView
import com.plusmobileapps.sample.workflow.characters.CharactersScreen
import com.plusmobileapps.sample.workflow.episodes.EpisodesListView
import com.plusmobileapps.sample.workflow.episodes.EpisodesScreen
import com.squareup.workflow1.ui.Screen
import com.squareup.workflow1.ui.compose.composeScreenViewFactory

data class BottomNavScreen(
    val selected: BottomNavItem,
    val child: Screen,
    val onTabSelected: (BottomNavItem) -> Unit
) : Screen

val BottomNavBinding = composeScreenViewFactory<BottomNavScreen> { rendering, environment ->
    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomNavItem.values().forEach {
                    val (icon, label) = when (it) {
                        BottomNavItem.CHARACTERS -> Icons.Default.Person to "Characters"
                        BottomNavItem.EPISODES -> Icons.Default.List to "Episodes"
                        BottomNavItem.LOCATIONS -> Icons.Default.LocationOn to "Locations"
                        BottomNavItem.ABOUT -> Icons.Default.Info to "About"
                    }
                    NavigationBarItem(
                        selected = rendering.selected == it,
                        onClick = { rendering.onTabSelected(it) },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) },
                    )
                }
            }
        }
    ) { paddingValues ->
        when (val child = rendering.child) {
            is CharactersScreen -> CharactersListView(
                modifier = Modifier.padding(paddingValues),
                rendering = child,
            )
            is EpisodesScreen -> EpisodesListView(
                modifier = Modifier.padding(paddingValues),
                rendering = child,
            )
        }
    }
}