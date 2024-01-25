package com.plusmobileapps.sample.workflow.characters

import com.plusmobileapps.rickandmorty.api.characters.CharacterDTO
import com.plusmobileapps.rickandmorty.api.characters.CharacterGender
import com.plusmobileapps.rickandmorty.api.characters.CharacterOrigin
import com.plusmobileapps.rickandmorty.api.characters.CharacterStatus

data class RickAndMortyCharacter(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: CharacterGender,
    val origin: CharacterOrigin,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
) {
    companion object {
        fun fromDto(dto: CharacterDTO) = RickAndMortyCharacter(
            id = dto.id,
            name = dto.name,
            status = dto.status,
            species = dto.species,
            type = dto.type,
            gender = dto.gender,
            origin = dto.origin,
            image = dto.image,
            episode = dto.episode,
            url = dto.url,
            created = dto.created
        )
    }
}
