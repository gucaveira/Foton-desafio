package com.gustavo.foton.desafio.core.domain.model

class CharacterPaging(
    val offset: Int,
    val total: Int,
    val characters: List<Character>
)