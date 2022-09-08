package com.plusmobileapps.sample.workflow.characters

import com.plusmobileapps.rickandmortysdk.characters.RickAndMortyCharacter
import com.squareup.workflow1.Worker
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CharactersWorkers @Inject constructor(private val repository: CharactersRepository) {

    init {
        repository.loadMore()
    }

    fun getCharacters(): Worker<List<RickAndMortyCharacter>> = Worker.create {
        repository.getCharacters().collect { characters ->
            emit(characters)
        }
    }
}