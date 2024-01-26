package com.plusmobileapps.sample.workflow.characters.detail

import com.plusmobileapps.sample.workflow.characters.CharactersRepository
import com.plusmobileapps.sample.workflow.characters.RickAndMortyCharacter
import com.plusmobileapps.sample.workflow.di.CharacterScope
import com.plusmobileapps.sample.workflow.di.SingleIn
import javax.inject.Inject

@SingleIn(CharacterScope::class)
class CharacterDetailRepository @Inject constructor(
    private val characterId: Int,
    private val charactersRepository: CharactersRepository
) {
    suspend fun getCharacter(): RickAndMortyCharacter? {
        return charactersRepository.getCharacter(characterId)
    }
}