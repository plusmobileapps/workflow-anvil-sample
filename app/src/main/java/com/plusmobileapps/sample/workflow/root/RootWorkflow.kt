package com.plusmobileapps.sample.workflow.root

import android.os.Parcelable
import com.plusmobileapps.sample.workflow.bottomnav.BottomNavWorkflow
import com.plusmobileapps.sample.workflow.characters.CharactersWorkflow
import com.plusmobileapps.sample.workflow.characters.search.CharacterSearchWorkflow
import com.plusmobileapps.sample.workflow.episodes.EpisodesWorkFlow
import com.plusmobileapps.sample.workflow.root.RootWorkflow.State
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

class RootWorkflow @Inject constructor(
    private val bottomNavWorkflow: BottomNavWorkflow,
    private val characterSearchWorkflow: CharacterSearchWorkflow,
) : StatefulWorkflow<Unit, State, Nothing, BackStackScreen<*>>() {

    @Parcelize
    sealed class State : Parcelable {
        object BottomNav : State()
        data class CharacterDetail(val id: Int) : State()
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): State = State.BottomNav

    override fun render(
        renderProps: Unit,
        renderState: State,
        context: RenderContext
    ): BackStackScreen<*> {
        val backstackScreens = mutableListOf<Screen>()
        backstackScreens += context.renderChild(
            bottomNavWorkflow,
            handler = this::onBottomNavOutput
        )

        when (renderState) {
            State.BottomNav -> Unit
            is State.CharacterDetail -> {
                backstackScreens += context.renderChild(
                    child = characterSearchWorkflow,
                    props = renderState.id,
                    handler = this::onCharacterDetailOutput,
                )
            }
        }

        return backstackScreens.toBackStackScreen()
    }

    override fun snapshotState(state: State): Snapshot? = state.toSnapshot()

    private fun onBottomNavOutput(output: BottomNavWorkflow.Output) = action {
        state = when (output) {
            is BottomNavWorkflow.Output.OpenCharacterDetail -> State.CharacterDetail(output.characterId)
        }
    }

    private fun onCharacterDetailOutput(output: CharacterSearchWorkflow.Output) = action {
        state = when (output) {
            CharacterSearchWorkflow.Output.Finished -> State.BottomNav
        }
    }

}