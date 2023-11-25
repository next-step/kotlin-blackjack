package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

abstract class Started(
    val hand: Hand = Hand(),
) : State2 {

    override fun getSum(): Int {
        return hand.getSum()
    }

    override fun receive(card: Card) {
        hand.receive(card)
    }

    override fun init(cards: List<Card>) {
        hand.init(cards)
    }

    override fun cards(): Hand {
        return hand
    }
}
