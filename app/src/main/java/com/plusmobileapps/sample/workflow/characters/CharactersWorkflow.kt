package com.plusmobileapps.sample.workflow.characters

import com.plusmobileapps.rickandmortysdk.RickAndMortySdk
import com.plusmobileapps.rickandmortysdk.characters.CharactersStore
import com.plusmobileapps.rickandmortysdk.characters.RickAndMortyCharacter
import com.plusmobileapps.sample.workflow.characters.CharactersWorkflow.Output
import com.plusmobileapps.sample.workflow.characters.CharactersWorkflow.State
import com.squareup.workflow1.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CharactersWorkflow @Inject constructor(private val workers: CharactersWorkers) :
    StatefulWorkflow<Unit, State, Output, CharactersScreen>() {

    data class State(val characters: List<RickAndMortyCharacter>, val isLoading: Boolean)
    sealed class Output {
        data class OpenCharacterDetail(val id: Int) : Output()
        object OpenEpisodes : Output()
    }

    override fun initialState(props: Unit, snapshot: Snapshot?) = State(
        isLoading = true,
        characters = emptyList()
    )

    override fun render(
        renderProps: Unit,
        renderState: State,
        context: RenderContext
    ): CharactersScreen {
        context.runningWorker(workers.getCharacters()) {
            action { state = State(isLoading = false, characters = it) }
        }
        return CharactersScreen(
            characters = renderState.characters,
            isLoading = renderState.isLoading,
            onCharacterClicked = {
                context.actionSink.send(action { setOutput(Output.OpenCharacterDetail(it.id)) })
            },
            goToEpisodeClicked = {
                context.actionSink.send(action { setOutput(Output.OpenEpisodes) })
            }
        )
    }

    override fun snapshotState(state: State): Snapshot? = null
}