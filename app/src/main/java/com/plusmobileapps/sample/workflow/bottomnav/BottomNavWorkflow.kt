package com.plusmobileapps.sample.workflow.bottomnav

import android.os.Parcelable
import com.plusmobileapps.sample.workflow.bottomnav.BottomNavWorkflow.Output
import com.plusmobileapps.sample.workflow.bottomnav.BottomNavWorkflow.State
import com.plusmobileapps.sample.workflow.characters.CharactersWorkflow
import com.plusmobileapps.sample.workflow.episodes.EpisodesWorkFlow
import com.squareup.workflow1.Snapshot
import com.squareup.workflow1.StatefulWorkflow
import com.squareup.workflow1.action
import com.squareup.workflow1.renderChild
import com.squareup.workflow1.ui.Screen
import com.squareup.workflow1.ui.container.BackStackScreen
import com.squareup.workflow1.ui.container.toBackStackScreen
import com.squareup.workflow1.ui.toSnapshot
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

enum class BottomNavItem {
    CHARACTERS,
    EPISODES,
    LOCATIONS,
    ABOUT,
}

class BottomNavWorkflow @Inject constructor(
    private val charactersWorkflow: CharactersWorkflow,
    private val episodesWorkflow: EpisodesWorkFlow,
) : StatefulWorkflow<Unit, State, Output, Screen>() {

    @Parcelize
    data class State(val selected: BottomNavItem) : Parcelable

    sealed class Output {
        data class OpenCharacterDetail(val characterId: Int) : Output()
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): State =
        State(BottomNavItem.CHARACTERS)

    override fun render(
        renderProps: Unit,
        renderState: State,
        context: RenderContext
    ): Screen {
        return BottomNavScreen(
            selected = renderState.selected,
            child = when (renderState.selected) {
                BottomNavItem.CHARACTERS -> context.renderChild(
                    child = charactersWorkflow,
                    handler = this::onCharactersOutput,
                )
                BottomNavItem.EPISODES -> context.renderChild(
                    child = episodesWorkflow,
                    handler = this::onEpisodesOutput
                )
                BottomNavItem.LOCATIONS -> TODO()
                BottomNavItem.ABOUT -> TODO()
            },
            onTabSelected = {
                context.actionSink.send(
                    action {
                        state = State(it)
                    }
                )
            }
        )
    }

    override fun snapshotState(state: State): Snapshot =
        state.toSnapshot()

    private fun onCharactersOutput(output: CharactersWorkflow.Output) = action {
        when (output) {
            is CharactersWorkflow.Output.OpenCharacterDetail -> setOutput(
                Output.OpenCharacterDetail(
                    output.id
                )
            )
        }
    }

    private fun onEpisodesOutput(output: EpisodesWorkFlow.Output) = action {
    }
}