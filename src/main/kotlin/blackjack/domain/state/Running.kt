package blackjack.domain.state

import blackjack.domain.Hand

sealed class Running(hand: Hand) : Started(hand) {
    override fun isFinished(): Boolean = false
}
