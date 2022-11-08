package com.plusmobileapps.sample.workflow.characters

import android.util.Log
import com.plusmobileapps.rickandmorty.api.RickAndMortyApiClient
import com.plusmobileapps.sample.workflow.di.AppScope
import com.plusmobileapps.sample.workflow.util.Dispatchers
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


interface CharactersRepository {

    fun getCharacters(): Flow<List<RickAndMortyCharacter>>

    fun loadMore()

    @Throws
    suspend fun getCharacter(id: Int): RickAndMortyCharacter?
}

@Singleton
@ContributesBinding(AppScope::class, CharactersRepository::class)
class CharactersRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApiClient,
    dispatchers: Dispatchers,
) : CharactersRepository {

    private val scope = CoroutineScope(dispatchers.io)

    private val characters = MutableStateFlow<List<RickAndMortyCharacter>>(emptyList())
    private var isLoading = false
    private var page = 1

    init {
        loadMore()
    }

    override fun getCharacters(): Flow<List<RickAndMortyCharacter>> = characters

    override fun loadMore() {
        if (isLoading) return
        isLoading = true
        scope.launch {
            try {
                val newCharacters = api.getCharacters(page).results.map {
                    RickAndMortyCharacter.fromDto(it)
                }
                characters.value = characters.value + newCharacters
                page += 1
            } catch (e: Exception) {
              Log.e(TAG, "Couldn't fetch characters", e)
            } finally {
                isLoading = false
            }
        }
    }

    override suspend fun getCharacter(id: Int): RickAndMortyCharacter? =
        characters.value.firstOrNull { it.id == id }

    companion object {
        const val TAG = "CharactersRepository"
    }
}