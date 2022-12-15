package com.plusmobileapps.sample.workflow.characters.search

import android.os.Parcelable
import com.plusmobileapps.sample.workflow.characters.CharactersRepository
import com.plusmobileapps.sample.workflow.characters.search.CharacterSearchWorkflow.Output
import com.plusmobileapps.sample.workflow.characters.search.CharacterSearchWorkflow.State
import com.squareup.workflow1.*
import com.squareup.workflow1.ui.Screen
import com.squareup.workflow1.ui.toSnapshot
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

class CharacterSearchWorkflow @Inject constructor(
    private val workers: CharacterSearchWorkers,
) : StatefulWorkflow<Int, State, Output, CharacterSearchScreen>() {

    sealed class State : Parcelable {
        @Parcelize
        object Loading : State()

        @Parcelize
        data class Loaded(val name: String) : State()
    }

    sealed class Output : Parcelable {
        @Parcelize
        object Finished : Output()
    }

    override fun initialState(props: Int, snapshot: Snapshot?): State =
        State.Loading

    override fun render(
        renderProps: Int,
        renderState: State,
        context: RenderContext
    ): CharacterSearchScreen {
        return when (renderState) {
            is State.Loaded -> CharacterSearchScreen(
                isLoading = false,
                name = renderState.name,
                onBack = context.eventHandler {
                    setOutput(Output.Finished)
                }
            )
            State.Loading -> {
                context.runningWorker(workers.getCharacter(renderProps)) {
                    action {
                        state = State.Loaded(it?.name.orEmpty())
                    }
                }
                CharacterSearchScreen(
                    isLoading = true,
                    onBack = context.eventHandler {
                        setOutput(Output.Finished)
                    }
                )
            }
        }
    }

    override fun snapshotState(state: State): Snapshot = state.toSnapshot()
}