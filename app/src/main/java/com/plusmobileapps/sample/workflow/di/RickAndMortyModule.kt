package com.plusmobileapps.sample.workflow.di

import android.content.Context
import com.plusmobileapps.rickandmortysdk.RickAndMorty
import com.plusmobileapps.rickandmortysdk.RickAndMortySdk
import com.plusmobileapps.rickandmortysdk.characters.CharactersStore
import dagger.Module
import dagger.Provides

@Module
object RickAndMortyModule {

    @Provides
    fun providesRickAndMortySdk(context: Context): RickAndMortySdk {
        return RickAndMorty.instance
    }

    @Provides
    fun providesCharactersStore(sdk: RickAndMortySdk): CharactersStore = sdk.charactersStore
}