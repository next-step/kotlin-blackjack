package next.step.blackjack.domain.player.state

import next.step.blackjack.domain.player.PlayerCards

sealed class PlayerState {
    abstract fun canHit(): Boolean
    abstract fun next(cards: PlayerCards): PlayerState
}
