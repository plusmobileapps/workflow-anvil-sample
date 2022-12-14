package com.plusmobileapps.sample.workflow.di

import com.plusmobileapps.sample.workflow.MainActivity
import com.squareup.anvil.annotations.MergeComponent
import javax.inject.Singleton

@Singleton
@MergeComponent(AppScope::class, modules = [AppModule::class, RickAndMortyModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}