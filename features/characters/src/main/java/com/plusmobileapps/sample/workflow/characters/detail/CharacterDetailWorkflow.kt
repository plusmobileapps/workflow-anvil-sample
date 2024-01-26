package com.plusmobileapps.sample.workflow.characters.detail

import com.plusmobileapps.sample.workflow.characters.CharactersRepository
import com.plusmobileapps.sample.workflow.characters.RickAndMortyCharacter
import com.plusmobileapps.sample.workflow.characters.detail.CharacterDetailWorkflow.State
import com.squareup.workflow1.SessionWorkflow
import com.squareup.workflow1.Snapshot
import com.squareup.workflow1.Worker
import com.squareup.workflow1.action
import com.squareup.workflow1.runningWorker
import com.squareup.workflow1.ui.Screen
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class CharacterDetailWorkflow @Inject constructor(
    private val factory: CharacterComponent.Factory,
) : SessionWorkflow<Int, State, Unit, Screen>() {

    data object State

    private lateinit var component: CharacterComponent
    private lateinit var childWorkflow: CharacterDetailChildWorkflow

    override fun initialState(
        props: Int,
        snapshot: Snapshot?,
        workflowScope: CoroutineScope
    ): State {
        component = factory.create(props)
        childWorkflow = component.characterDetailWorkflow()
        return State
    }

    override fun render(renderProps: Int, renderState: State, context: RenderContext): Screen {
        return context.renderChild(
            child = childWorkflow,
            props = renderProps,
            handler = {
                action { setOutput(Unit) }
            }
        )
    }

    override fun snapshotState(state: State): Snapshot? = null
}