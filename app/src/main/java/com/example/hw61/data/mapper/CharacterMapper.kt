package com.example.hw61.data.mapper

import com.example.hw61.data.model.cartoon.CharacterDto
import com.example.hw61.domain.model.Character

fun CharacterDto.toDomain(): Character {
    return Character(
        id = this.id ?: 0,
        name = this.name.orEmpty(),
        status = this.status.orEmpty(),
        species = this.species.orEmpty(),
        image = this.image.orEmpty(),
        gender = this.gender.orEmpty(),
    )
}
