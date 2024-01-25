package com.plusmobileapps.sample.workflow.root

import com.plusmobileapps.sample.workflow.characters.CharacterDetailWorkflow
import com.plusmobileapps.sample.workflow.characters.CharactersWorkflow
import com.plusmobileapps.sample.workflow.episodes.EpisodesWorkFlow
import com.plusmobileapps.sample.workflow.root.RootWorkflow.State
import com.squareup.workflow1.Snapshot
import com.squareup.workflow1.StatefulWorkflow
import com.squareup.workflow1.action
import com.squareup.workflow1.renderChild
import com.squareup.workflow1.ui.Screen
import com.squareup.workflow1.ui.navigation.BackStackScreen
import com.squareup.workflow1.ui.navigation.toBackStackScreen
import javax.inject.Inject

class RootWorkflow @Inject constructor(
    private val episodesWorkflow: EpisodesWorkFlow,
    private val charactersWorkflow: CharactersWorkflow,
    private val characterDetailWorkflow: CharacterDetailWorkflow
) : StatefulWorkflow<Unit, State, Nothing, BackStackScreen<*>>() {
    sealed class State {

        data class CharacterDetail(val id: Int) : State()
        object Episodes : State()
        object Characters : State()
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): State = State.Characters

    override fun render(
        renderProps: Unit,
        renderState: State,
        context: RenderContext
    ): BackStackScreen<*> {
        val backstackScreens = mutableListOf<Screen>()
        backstackScreens += context.renderChild(
            charactersWorkflow,
            handler = this::onCharactersOutput
        )
        when (renderState) {
            State.Characters -> {/* always added to stack */
            }

            State.Episodes -> {
                backstackScreens += context.renderChild(
                    episodesWorkflow,
                    handler = this::onEpisodesOutput
                )
            }

            is State.CharacterDetail -> {
                backstackScreens += context.renderChild(
                    child = characterDetailWorkflow,
                    props = renderState.id,
                    handler = {
                        action { state = State.Characters }
                    }
                )
            }
        }

        return backstackScreens.toBackStackScreen()
    }

    override fun snapshotState(state: State): Snapshot? = null

    private fun onEpisodesOutput(output: EpisodesWorkFlow.Output) = action {
        state = when (output) {
            is EpisodesWorkFlow.Output.OpenEpisodeDetail -> TODO()
            EpisodesWorkFlow.Output.GoBackToCharacter -> State.Characters
        }
    }

    private fun onCharactersOutput(output: CharactersWorkflow.Output) = action {
        state = when (output) {
            is CharactersWorkflow.Output.OpenCharacterDetail -> State.CharacterDetail(output.id)
            CharactersWorkflow.Output.OpenEpisodes -> State.Episodes
        }
    }


}