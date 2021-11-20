package blackjack.domain.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.exception.UnsupportedDrawException

class Blackjack(
    override val cards: Cards
) : Finished() {
    override fun draw(card: Card): State {
        throw UnsupportedDrawException()
    }

    override fun toString(): String = "Blackjack"
}
