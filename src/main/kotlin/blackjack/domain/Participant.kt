package blackjack.domain

import blackjack.domain.card.Cards

sealed class Participant(
    private val state: State
) {
    fun cards(): Cards {
        return state.cards()
    }
}
