package blackjack.domain.state

import blackjack.domain.card.Cards

class Blackjack(
    private val cards: Cards
) : State {
    override fun cards(): Cards {
        return cards
    }
}
