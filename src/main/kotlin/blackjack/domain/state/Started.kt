package blackjack.domain.state

import blackjack.domain.Hand

abstract class Started(
    val hand: Hand = Hand(),
) : State2 {

    override fun getSum(): Int {
        return hand.getSum()
    }
}
