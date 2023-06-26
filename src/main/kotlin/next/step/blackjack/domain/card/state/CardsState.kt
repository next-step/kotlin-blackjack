package next.step.blackjack.domain.card.state

import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult

sealed class CardsState {

    abstract fun next(cards: Cards): CardsState
    abstract fun fight(other: CardsState): GameResult
}
