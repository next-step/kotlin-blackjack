package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

abstract class Started(
    val hand: Hand = Hand(),
) : State {

    override fun init(cards: List<Card>): State {
        hand.init(cards)
        return if (getSum() == 21) {
            Blackjack(hand)
        } else {
            Hit(hand)
        }
    }

    override fun getSum(): Int {
        return hand.getSum()
    }

    override fun cards(): Hand {
        return hand
    }
}
