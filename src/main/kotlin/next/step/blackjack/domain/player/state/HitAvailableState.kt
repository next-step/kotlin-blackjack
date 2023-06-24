package next.step.blackjack.domain.player.state

import next.step.blackjack.domain.player.PlayerCards

object HitAvailableState : PlayerState {
    override fun canHit(): Boolean = true

    override fun next(cards: PlayerCards): PlayerState = when {
        cards.isBlackJack() -> BlackjackState
        cards.isFinished() -> FinishedState
        cards.isBurst() -> BurstState
        else -> HitAvailableState
    }
}
