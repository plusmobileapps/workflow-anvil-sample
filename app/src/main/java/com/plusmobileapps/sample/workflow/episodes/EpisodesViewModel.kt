package com.plusmobileapps.sample.workflow.episodes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(repository: EpisodesRepository) : ViewModel() {

    var state: List<String> by mutableStateOf(emptyList())
        private set

    init {
        viewModelScope.launch {
            val episodes = repository.getEpisodes()
            state = episodes
        }
    }

}