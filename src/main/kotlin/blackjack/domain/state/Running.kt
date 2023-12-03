package blackjack.domain.state

import blackjack.domain.card.Cards

sealed class Running(
    private val cards: Cards
): State {
    override fun cards(): Cards {
        return cards
    }
}
