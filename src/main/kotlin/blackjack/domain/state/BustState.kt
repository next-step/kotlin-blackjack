package blackjack.domain.state

import blackjack.domain.PlayerCards
import blackjack.domain.State

internal class BustState(cards: PlayerCards) : State(cards) {
    override val hittable: Boolean = false
    override val earningsRate: Double = 0.0
}
