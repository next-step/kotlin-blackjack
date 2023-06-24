package next.step.blackjack.domain.player.state

import next.step.blackjack.domain.player.PlayerCards

interface PlayerState {
    fun canHit(): Boolean
    fun next(cards: PlayerCards): PlayerState
}