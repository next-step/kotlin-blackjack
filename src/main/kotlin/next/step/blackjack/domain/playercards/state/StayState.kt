package next.step.blackjack.domain.playercards.state

import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult

object StayState : PlayerCardsState() {

    override fun next(cards: Cards): PlayerCardsState = when {
        cards.isBlackjack() -> BlackjackState
        cards.isHit() -> HitState
        cards.isBurst() -> BurstState
        else -> StayState
    }

    override fun fight(other: PlayerCardsState): GameResult =
        when (other) {
            BurstState -> GameResult.WIN
            BlackjackState -> GameResult.LOSE
            HitState -> GameResult.LOSE
            StayState -> GameResult.UNDECIDED
        }
}
