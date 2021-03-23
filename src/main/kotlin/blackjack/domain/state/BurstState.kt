package blackjack.domain.state

import blackjack.domain.PlayerCards
import blackjack.domain.State

internal class BurstState(cards: PlayerCards) : State(cards) {
    override fun canHit(max: Int): Boolean {
        return false
    }

    override fun earningsRate(): Double {
        return 0.0
    }
}
