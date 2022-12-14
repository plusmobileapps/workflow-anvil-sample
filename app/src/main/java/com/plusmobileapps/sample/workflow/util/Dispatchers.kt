package com.plusmobileapps.sample.workflow.util

import com.plusmobileapps.sample.workflow.di.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

interface Dispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

@Singleton
@ContributesBinding(AppScope::class)
class DispatchersImpl @Inject constructor() : Dispatchers {
    override val main: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main
    override val io: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO
    override val default: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Default
    override val unconfined: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Unconfined
}