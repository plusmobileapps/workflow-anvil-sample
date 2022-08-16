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
import androidx.compose.ui.tooling.preview.Preview
import com.plusmobileapps.sample.workflow.episodes.EpisodesViewModel
import com.plusmobileapps.sample.workflow.ui.theme.SquareSampleAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject lateinit var viewModel: EpisodesViewModel

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
                    EpisodesList(viewModel.state)
                }
            }
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