package blackjack.domain.state

import blackjack.domain.card.Cards

sealed class Finished(
    private val cards: Cards
) : State {
    override fun cards(): Cards {
        return cards
    }
}
