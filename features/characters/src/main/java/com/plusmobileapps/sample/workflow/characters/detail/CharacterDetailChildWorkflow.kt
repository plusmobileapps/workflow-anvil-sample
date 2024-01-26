package com.plusmobileapps.sample.workflow.characters.detail

import com.plusmobileapps.sample.workflow.characters.RickAndMortyCharacter
import com.plusmobileapps.sample.workflow.characters.detail.RealCharacterDetailChildWorkflow.State
import com.plusmobileapps.sample.workflow.di.CharacterScope
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.workflow1.Snapshot
import com.squareup.workflow1.StatefulWorkflow
import com.squareup.workflow1.Worker
import com.squareup.workflow1.Workflow
import com.squareup.workflow1.action
import com.squareup.workflow1.runningWorker
import com.squareup.workflow1.ui.Screen
import javax.inject.Inject

interface CharacterDetailChildWorkflow : Workflow<Int, Unit, Screen>

@ContributesBinding(
    scope = CharacterScope::class,
    boundType = CharacterDetailChildWorkflow::class,
)
class RealCharacterDetailChildWorkflow @Inject constructor(
    private val characterDetailRepository: CharacterDetailRepository,
) : CharacterDetailChildWorkflow, StatefulWorkflow<Int, State, Unit, Screen>() {
    sealed class State {
        data object Loading : State()
        data class Loaded(val character: RickAndMortyCharacter) : State()
    }

    override fun initialState(props: Int, snapshot: Snapshot?): State = State.Loading

    override fun render(renderProps: Int, renderState: State, context: RenderContext): Screen {
        context.runningWorker(
            worker = Worker.from { characterDetailRepository.getCharacter() },
            key = "character detail worker",
            handler = {
                action {
                    state = State.Loaded(it!!)
                }
            }
        )
        return when (renderState) {
            State.Loading -> CharacterDetailScreen(
                renderProps,
                "Loading...",
                onBackClick = context.eventHandler { setOutput(Unit) }
            )

            is State.Loaded -> CharacterDetailScreen(
                renderProps,
                "Child workflow - ${renderState.character.name}",
                onBackClick = context.eventHandler { setOutput(Unit) }
            )
        }
    }

    override fun snapshotState(state: State): Snapshot? = null
}