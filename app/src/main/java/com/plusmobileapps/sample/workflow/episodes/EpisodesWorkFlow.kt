package com.plusmobileapps.sample.workflow.episodes

import com.plusmobileapps.sample.workflow.episodes.EpisodesWorkFlow.Output
import com.plusmobileapps.sample.workflow.episodes.EpisodesWorkFlow.State
import com.squareup.workflow1.Snapshot
import com.squareup.workflow1.StatefulWorkflow
import com.squareup.workflow1.action
import com.squareup.workflow1.runningWorker
import javax.inject.Inject


class EpisodesWorkFlow @Inject constructor(private val workers: EpisodesWorkers) : StatefulWorkflow<Unit, State, Output, EpisodesScreen>() {

    data class State(val isLoading: Boolean = true, val episodes: List<String> = emptyList())
    object Output

    override fun initialState(
        props: Unit,
        snapshot: Snapshot?
    ): State = State()

    override fun render(
        renderProps: Unit,
        renderState: State,
        context: RenderContext
    ): EpisodesScreen {
        context.runningWorker(workers.getEpisodes()) {
            action {
                state = State(isLoading = false, episodes = it)
            }
        }
        return EpisodesScreen(
            episodes = renderState.episodes,
            isLoading = renderState.isLoading,
            onEpisodeClicked = {
                // TODO
            }
        )
    }

    override fun snapshotState(state: State): Snapshot? = Snapshot.of(state.toString())
}
