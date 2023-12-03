package blackjack.domain.state

import blackjack.domain.card.Cards

class Hit(
    private val cards: Cards
): State {
    override fun cards(): Cards {
        return cards
    }
}
