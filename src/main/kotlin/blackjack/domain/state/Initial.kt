package blackjack.domain.state

import blackjack.domain.Hand

object Initial {
    fun initialState(hand: Hand): State {
        return when (hand.isBlackjack()) {
            true -> Blackjack(hand)
            false -> Hit(hand)
        }
    }
}
