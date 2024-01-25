package com.plusmobileapps.sample.workflow.characters

import com.plusmobileapps.sample.workflow.di.AppScope
import com.plusmobileapps.sample.workflow.di.CharacterScope
import com.plusmobileapps.sample.workflow.di.SingleIn
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.ContributesTo

@SingleIn(CharacterScope::class)
@ContributesSubcomponent(
    scope = CharacterScope::class,
    parentScope = AppScope::class,
)
interface CharacterComponent {

    @ContributesSubcomponent.Factory
    interface Factory {
        fun create(): CharacterComponent
    }

    @ContributesTo(AppScope::class)
    interface ParentBindings {
        fun characterComponentBuilder(): Factory
    }
}