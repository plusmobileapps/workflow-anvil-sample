package com.plusmobileapps.sample.workflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plusmobileapps.sample.workflow.characters.CharactersBinding
import com.plusmobileapps.sample.workflow.episodes.EpisodesBinding
import com.plusmobileapps.sample.workflow.ext.assistedViewModel
import com.plusmobileapps.sample.workflow.root.RootWorkflow
import com.plusmobileapps.sample.workflow.ui.theme.SquareSampleAppTheme
import com.squareup.workflow1.ui.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val viewRegistry = ViewRegistry(
//    BackStackContainer,
    EpisodesBinding,
    CharactersBinding
)

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory: MainViewModel.Factory

    private val viewModel: MainViewModel by assistedViewModel { factory.create(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApplication).appComponent.inject(this)
        setContent {
            SquareSampleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val lifecycle = LocalLifecycleOwner.current
                    AndroidView(factory = { WorkflowLayout(it) }) {
                        it.take(
                            lifecycle = lifecycle.lifecycle,
                            renderings = viewModel.renderings.map { screen ->
                                screen.withRegistry(viewRegistry)
                            }
                        )
                    }
                }
            }
        }
    }

    class MainViewModel @AssistedInject constructor(
        @Assisted val savedStateHandle: SavedStateHandle,
        val rootWorkflow: RootWorkflow
    ) : ViewModel() {
        val renderings: StateFlow<Screen> = renderWorkflowIn(
            workflow = rootWorkflow,
            scope = viewModelScope,
            savedStateHandle = savedStateHandle
        )

        @AssistedFactory
        interface Factory {
            fun create(savedStateHandle: SavedStateHandle): MainViewModel
        }
    }
}

@Composable
fun EpisodesList(episodes: List<String>) {
    LazyColumn {
        items(episodes) {
            Text(text = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SquareSampleAppTheme {
        EpisodesList(listOf("Rickmurai Jack", "Pickle Rick"))
    }
}