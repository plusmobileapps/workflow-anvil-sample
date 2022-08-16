package com.plusmobileapps.sample.workflow.episodes

import com.plusmobileapps.sample.workflow.di.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject
import javax.inject.Singleton

interface EpisodesRepository {
    suspend fun getEpisodes(): List<String>
}

@Singleton
@ContributesBinding(scope = AppScope::class, boundType = EpisodesRepository::class)
class EpisodesRepositoryImpl @Inject constructor() : EpisodesRepository {
    override suspend fun getEpisodes(): List<String> = listOf(
        "Pickle Rick",
        "Rickmurai Jack"
    )
}