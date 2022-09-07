package com.plusmobileapps.sample.workflow.episodes

import com.squareup.workflow1.Worker
import javax.inject.Inject

class EpisodesWorkers @Inject constructor(private val repository: EpisodesRepository) {

    fun getEpisodes(): Worker<List<Episode>> = Worker.from {
        repository.getEpisodes()
    }

}