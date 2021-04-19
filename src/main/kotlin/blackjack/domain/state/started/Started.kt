package blackjack.domain.state.started

import blackjack.domain.card.Cards
import blackjack.domain.state.State

abstract class Started(
    val cards: Cards
) : State {
    override val cardNames: List<String>
        get() = cards.cardNames
    override val cardSize: Int
        get() = cards.size

    override fun cardPointSum(): Int {
        return cards.calculatePoint()
    }
}
