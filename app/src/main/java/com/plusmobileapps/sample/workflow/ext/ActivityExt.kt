package com.plusmobileapps.sample.workflow.ext

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * Get a reference to a [ViewModel] that is assisted injected with a [SavedStateFlowHandle] that is scoped
 * to a [FragmentActivity]
 */
inline fun <reified T : ViewModel> ComponentActivity.assistedViewModel(
    crossinline viewModelProducer: (SavedStateHandle) -> T
): Lazy<T> = viewModels {
    object : AbstractSavedStateViewModelFactory(this, intent.extras) {
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ) = viewModelProducer(handle) as T
    }
}