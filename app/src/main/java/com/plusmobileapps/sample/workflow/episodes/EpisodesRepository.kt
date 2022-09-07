package com.plusmobileapps.sample.workflow.episodes

import com.plusmobileapps.sample.workflow.di.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject
import javax.inject.Singleton

data class Episode(val id: Int, val name: String)

interface EpisodesRepository {
    suspend fun getEpisodes(): List<Episode>
}

@Singleton
@ContributesBinding(scope = AppScope::class, boundType = EpisodesRepository::class)
class EpisodesRepositoryImpl @Inject constructor() : EpisodesRepository {
    override suspend fun getEpisodes(): List<Episode> = listOf(
        Episode(1, "Pickle Rick"),
        Episode(2, "Rickmurai Jack"),
    )
}