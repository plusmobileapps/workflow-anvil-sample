package com.plusmobileapps.sample.workflow.di

import com.plusmobileapps.rickandmorty.api.RickAndMortyApi
import com.plusmobileapps.rickandmorty.api.RickAndMortyApiClient
import dagger.Module
import dagger.Provides

@Module
object RickAndMortyModule {

    @Provides
    fun providesRickAndMortySdk(): RickAndMortyApiClient = RickAndMortyApi.instance
}