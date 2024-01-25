package com.plusmobileapps.sample.workflow.characters

import com.plusmobileapps.sample.workflow.di.CharacterScope
import com.squareup.anvil.annotations.ContributesTo

@ContributesTo(CharacterScope::class)
interface CharacterBindings {
    fun inject(workflow: CharacterDetailWorkflow)
}