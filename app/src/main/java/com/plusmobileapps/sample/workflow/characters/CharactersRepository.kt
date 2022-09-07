package com.plusmobileapps.sample.workflow.characters

import com.plusmobileapps.sample.workflow.di.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject
import javax.inject.Singleton

data class RickAndMortyCharacter(val id: Int, val name: String)

interface CharactersRepository {
    suspend fun getCharacters(): List<RickAndMortyCharacter>
}

@Singleton
@ContributesBinding(AppScope::class, CharactersRepository::class)
class CharactersRepositoryImpl @Inject constructor() : CharactersRepository {
    override suspend fun getCharacters(): List<RickAndMortyCharacter> {
        return listOf(
            RickAndMortyCharacter(0, "Rick Sanchez"),
            RickAndMortyCharacter(1, "Pickle Rick"),
        )
    }
}