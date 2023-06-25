package next.step.blackjack.domain.card.state

import next.step.blackjack.domain.card.Cards

sealed class CardsState {

    abstract fun next(cards: Cards): CardsState
}
