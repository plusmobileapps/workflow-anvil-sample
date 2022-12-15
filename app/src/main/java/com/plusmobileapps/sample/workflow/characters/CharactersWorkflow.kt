package com.plusmobileapps.sample.workflow.characters

import com.plusmobileapps.sample.workflow.characters.CharactersWorkflow.Output
import com.plusmobileapps.sample.workflow.characters.CharactersWorkflow.State
import com.squareup.workflow1.*
import javax.inject.Inject

class CharactersWorkflow @Inject constructor(private val workers: CharactersWorkers) :
    StatefulWorkflow<Unit, State, Output, CharactersScreen>() {

    data class State(val characters: List<RickAndMortyCharacter>, val isLoading: Boolean)
    sealed class Output {
        data class OpenCharacterDetail(val id: Int) : Output()
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
            onCharacterClicked = context.eventHandler { character ->
                setOutput(Output.OpenCharacterDetail(character.id))
            },
            onLoadMoreClicked = {
                context.actionSink.send(action {
                    workers.loadMoreCharacters()
                    state = state.copy(isLoading = true)
                })
            }
        )
    }

    override fun snapshotState(state: State): Snapshot? = null
}