package blackjack.domain.state.result

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.domain.state.Finished
import blackjack.domain.state.State
import blackjack.exception.UnsupportedDrawException

class Lose(
    override val cards: Cards,
) : Finished() {
    override fun draw(card: Card): State {
        throw UnsupportedDrawException()
    }

    override fun toString(): String = "패"
}
