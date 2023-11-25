package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

abstract class Started(
    val hand: Hand = Hand(),
) : State2 {

    override fun getSum(): Int {
        return hand.getSum()
    }

    override fun init(cards: List<Card>): State2 {
        hand.init(cards)
        return if (getSum() == 21) {
            Blackjack(hand)
        } else {
            Hit(hand)
        }
    }

    override fun cards(): Hand {
        return hand
    }
}
