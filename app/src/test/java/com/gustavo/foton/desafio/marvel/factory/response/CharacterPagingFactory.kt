package com.gustavo.foton.desafio.marvel.factory.response

import com.gustavo.foton.desafio.core.domain.model.Character
import com.gustavo.foton.desafio.core.domain.model.CharacterPaging

class CharacterPagingFactory {

    fun create() = CharacterPaging(
        offset = 0, total = 2, characters = listOf(
            Character(
                id = 1011334,
                name = "3-D Man",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ), Character(
                id = 1017100,
                name = "A-Bomb (HAS)",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            )
        )
    )
}