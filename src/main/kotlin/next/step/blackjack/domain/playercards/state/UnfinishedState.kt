package next.step.blackjack.domain.playercards.state

import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult

object UnfinishedState : PlayerCardsState() {

    override fun next(cards: Cards): PlayerCardsState = when {
        cards.isBlackjack() -> BlackjackState
        cards.isFinished() -> FinishedState
        cards.isBurst() -> BurstState
        else -> UnfinishedState
    }

    override fun fight(other: PlayerCardsState): GameResult =
        when (other) {
            BurstState -> GameResult.WIN
            BlackjackState -> GameResult.LOSE
            FinishedState -> GameResult.LOSE
            UnfinishedState -> GameResult.UNDECIDED
        }
}
