
package com.plusmobileapps.sample.workflow.characters

import com.plusmobileapps.sample.workflow.characters.CharacterDetailWorkflow.State
import com.squareup.workflow1.SessionWorkflow
import com.squareup.workflow1.Snapshot
import com.squareup.workflow1.runningWorker
import com.squareup.workflow1.ui.Screen
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class CharacterDetailWorkflow @Inject constructor(
    private val factory: CharacterComponent.Factory
): SessionWorkflow<Int, State, Unit, Screen>() {
    private lateinit var component: CharacterComponent
    sealed class State {
        data object Loading : State()
        data class Loaded(val character: RickAndMortyCharacter) : State()
    }

    override fun initialState(
        props: Int,
        snapshot: Snapshot?,
        workflowScope: CoroutineScope
    ): State {
        component = factory.create(props)
        return State.Loading
    }

    override fun render(renderProps: Int, renderState: State, context: RenderContext): Screen {
        return CharacterDetailScreen(
            characterId = renderProps,
            name = "name"
        )
    }

    override fun snapshotState(state: State): Snapshot? = null
}