package blackjack.domain.card.state

import blackjack.domain.card.Cards
import blackjack.domain.card.Score

abstract class AbstractState(override val cards: Cards) : State {

    override val score: Score
        get() = cards.score

    override fun stay(): State = Stay(cards)
}
