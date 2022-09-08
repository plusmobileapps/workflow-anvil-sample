package com.plusmobileapps.sample.workflow.characters

import com.plusmobileapps.rickandmortysdk.RickAndMortySdk
import com.plusmobileapps.rickandmortysdk.characters.RickAndMortyCharacter
import com.plusmobileapps.sample.workflow.di.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


interface CharactersRepository {
    suspend fun getCharacters(): Flow<List<RickAndMortyCharacter>>
    fun loadMore()
    @Throws
    suspend fun getCharacter(id: Int): RickAndMortyCharacter?
}

@Singleton
@ContributesBinding(AppScope::class, CharactersRepository::class)
class CharactersRepositoryImpl @Inject constructor(private val rickAndMortySdk: RickAndMortySdk) :
    CharactersRepository {
    override suspend fun getCharacters(): Flow<List<RickAndMortyCharacter>> {
        return rickAndMortySdk.charactersStore.getCharacters()
    }

    override fun loadMore() {
        rickAndMortySdk.charactersStore.loadNextPage()
    }

    override suspend fun getCharacter(id: Int): RickAndMortyCharacter? {
        return try {
            rickAndMortySdk.charactersStore.getCharacter(id)
        }catch (e: Exception) {
            null
        }
    }
}