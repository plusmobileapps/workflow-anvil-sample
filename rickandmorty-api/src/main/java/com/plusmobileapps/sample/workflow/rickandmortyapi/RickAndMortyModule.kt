package com.plusmobileapps.sample.workflow.rickandmortyapi

import com.plusmobileapps.rickandmorty.api.RickAndMortyApi
import com.plusmobileapps.rickandmorty.api.RickAndMortyApiClient
import com.plusmobileapps.sample.workflow.di.AppScope
import com.plusmobileapps.sample.workflow.di.SingleIn
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@Module
@ContributesTo(AppScope::class)
class RickAndMortyModule {

    @SingleIn(AppScope::class)
    @Provides
    fun provideRickAndMortyApiClient(): RickAndMortyApiClient {
        return RickAndMortyApi.instance
    }
}