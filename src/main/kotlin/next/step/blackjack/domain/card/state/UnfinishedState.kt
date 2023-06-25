package next.step.blackjack.domain.card.state

import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult

object UnfinishedState : CardsState() {

    override fun next(cards: Cards): CardsState = when {
        cards.isBlackjack() -> BlackjackState
        cards.isFinished() -> FinishedState
        cards.isBurst() -> BurstState
        else -> UnfinishedState
    }

    override fun fight(other: CardsState): GameResult =
        when (other) {
            BurstState -> GameResult.WIN
            BlackjackState -> GameResult.LOSE
            FinishedState -> GameResult.LOSE
            UnfinishedState -> GameResult.UNDECIDED
        }
}
