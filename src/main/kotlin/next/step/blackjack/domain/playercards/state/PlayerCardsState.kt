package next.step.blackjack.domain.playercards.state

import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult

sealed class PlayerCardsState {

    abstract fun next(cards: Cards): PlayerCardsState
    abstract fun fight(other: PlayerCardsState): GameResult
}
