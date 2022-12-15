package com.plusmobileapps.sample.workflow.characters.search

import com.plusmobileapps.sample.workflow.characters.CharactersRepository
import com.plusmobileapps.sample.workflow.characters.RickAndMortyCharacter
import com.squareup.workflow1.Worker
import javax.inject.Inject

class CharacterSearchWorkers @Inject constructor(
    private val repository: CharactersRepository,
) {

    fun getCharacter(id: Int): Worker<RickAndMortyCharacter?> =
        Worker.from { repository.getCharacter(id) }
}