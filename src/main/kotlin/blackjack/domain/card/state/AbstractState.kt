package blackjack.domain.card.state

import blackjack.domain.card.Cards

abstract class AbstractState(val cards: Cards) : State {
    override fun stay(): State = Stay(cards)
}
