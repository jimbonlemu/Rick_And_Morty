package com.jimbonlemu.rickandmorty.core.utils

import com.jimbonlemu.rickandmorty.core.data.local.entity.CharacterEntity
import com.jimbonlemu.rickandmorty.core.data.remote.response.CharacterItem
import com.jimbonlemu.rickandmorty.core.domain.model.Character

object DataMapper {
    fun mapResponsesToEntities(input: List<CharacterItem>): List<CharacterEntity> {
        return input.map { item ->
            CharacterEntity(
                id = item.id,
                image = item.image,
                gender = item.gender,
                species = item.species,
                originName = item.origin.name,
                name = item.name,
                locationName = item.location.name,
                type = item.type,
                status = item.status,
                isFavorite = false
            )
        }
    }

    fun mapEntitiesToDomain(input: List<CharacterEntity>): List<Character> =
        input.map { entity ->
            Character(
                id = entity.id,
                image = entity.image,
                gender = entity.gender,
                species = entity.species,
                originName = entity.originName,
                name = entity.name,
                locationName = entity.locationName,
                type = entity.type,
                status = entity.status,
                isFavorite = entity.isFavorite
            )
        }

    fun mapDomainToEntity(input: Character) = CharacterEntity(
        id = input.id,
        image = input.image,
        gender = input.gender,
        species = input.species,
        originName = input.originName,
        name = input.name,
        locationName = input.locationName,
        type = input.type,
        status = input.status,
        isFavorite = input.isFavorite,
    )
}
