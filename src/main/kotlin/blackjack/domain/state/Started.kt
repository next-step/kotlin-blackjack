package blackjack.domain.state

import blackjack.domain.Hand

sealed class Started(private val hand: Hand) : State {
    override fun hand() = hand
}
