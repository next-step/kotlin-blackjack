package blackjack.domain.state

import blackjack.domain.PlayerCards
import blackjack.domain.State

internal class HittableState(cards: PlayerCards) : State(cards) {
    override val hittable: Boolean = true
    override val earningsRate: Double get() = 1.0
}
