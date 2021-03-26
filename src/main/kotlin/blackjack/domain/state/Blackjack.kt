package blackjack.domain.state

import blackjack.domain.PlayerCards
import blackjack.domain.State

internal class Blackjack(cards: PlayerCards) : State(cards) {
    override val hittable: Boolean = false
    override val earningsRate: Double = 2.0
}
