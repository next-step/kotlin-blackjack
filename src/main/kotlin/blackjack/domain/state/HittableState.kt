package blackjack.domain.state

import blackjack.domain.PlayerCards
import blackjack.domain.State

internal class HittableState(cards: PlayerCards) : State(cards) {
    override fun canHit(max: Int): Boolean {
        return this.score() < max
    }

    override fun earningsRate(): Double {
        return 1.0
    }
}
