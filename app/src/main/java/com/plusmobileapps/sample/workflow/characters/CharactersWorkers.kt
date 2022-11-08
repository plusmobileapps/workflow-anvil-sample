package com.plusmobileapps.sample.workflow.characters

import com.squareup.workflow1.Worker
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

    fun loadMoreCharacters() {
        repository.loadMore()
    }
}