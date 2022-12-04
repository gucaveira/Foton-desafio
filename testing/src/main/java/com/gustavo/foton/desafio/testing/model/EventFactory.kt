package com.gustavo.foton.desafio.testing.model

import com.gustavo.foton.desafio.core.domain.model.Event

class EventFactory {
    fun create(comic: FakeEvent) = when (comic) {
        FakeEvent.FakeEvent1 -> Event(
            1, "http://fakeurl.jpg"
        )
    }

    sealed class FakeEvent {
        object FakeEvent1 : FakeEvent()
    }
}