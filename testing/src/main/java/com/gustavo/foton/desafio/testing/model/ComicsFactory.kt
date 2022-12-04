package com.gustavo.foton.desafio.testing.model

import com.gustavo.foton.desafio.core.domain.model.Comic

class ComicsFactory {

    fun create(comic: FakeComic) = when (comic) {
        FakeComic.FakeComic1 -> Comic(
            22111506,
            "http://fakecomigurl.jpg"
        )
    }

    sealed class FakeComic {
        object FakeComic1 : FakeComic()
    }
}