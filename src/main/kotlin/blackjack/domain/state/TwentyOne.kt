package blackjack.domain.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.exception.UnsupportedDrawException

class TwentyOne(
    val cards: Cards,
) : Finished() {
    override fun draw(card: Card): State {
        throw UnsupportedDrawException()
    }

    override fun currentCards(): Cards = cards
}
