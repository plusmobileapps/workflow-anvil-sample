package com.plusmobileapps.sample.workflow.characters

import com.squareup.workflow1.Worker
import javax.inject.Inject

class CharactersWorkers @Inject constructor(private val repository: CharactersRepository) {
    fun getCharacters(): Worker<List<RickAndMortyCharacter>> = Worker.from {
        repository.getCharacters()
    }
}