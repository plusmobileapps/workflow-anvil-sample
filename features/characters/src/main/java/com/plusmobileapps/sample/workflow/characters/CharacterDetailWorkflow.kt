
package com.plusmobileapps.sample.workflow.characters

import com.plusmobileapps.sample.workflow.characters.CharacterDetailWorkflow.State
import com.squareup.workflow1.SessionWorkflow
import com.squareup.workflow1.Snapshot
import com.squareup.workflow1.ui.Screen
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class CharacterDetailWorkflow @Inject constructor(
    private val factory: CharacterComponent.Factory
): SessionWorkflow<Unit, State, Nothing, Screen>() {
    private lateinit var component: CharacterComponent
    sealed class State {
        data object Loading : State()
//        data class Loading(val characterId: ) : State()
//        data class
    }

    override fun initialState(
        props: Unit,
        snapshot: Snapshot?,
        workflowScope: CoroutineScope
    ): State {
        component = factory.create()
        return State.Loading
    }

    override fun render(renderProps: Unit, renderState: State, context: RenderContext): Screen {
        TODO("Not yet implemented")
    }

    override fun snapshotState(state: State): Snapshot? = null
}