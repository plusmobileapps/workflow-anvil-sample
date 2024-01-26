package com.plusmobileapps.sample.workflow.characters.detail

import com.plusmobileapps.sample.workflow.di.AppScope
import com.plusmobileapps.sample.workflow.di.CharacterScope
import com.plusmobileapps.sample.workflow.di.SingleIn
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.ContributesTo
import dagger.BindsInstance

@SingleIn(CharacterScope::class)
@ContributesSubcomponent(
    scope = CharacterScope::class,
    parentScope = AppScope::class,
)
interface CharacterComponent {

    fun characterDetailWorkflow(): CharacterDetailChildWorkflow

    @ContributesSubcomponent.Factory
    interface Factory {
        fun create(@BindsInstance characterId: Int): CharacterComponent
    }

    @ContributesTo(AppScope::class)
    interface ParentBindings {
        fun characterComponentBuilder(): Factory
    }
}