package state

import Hand
import card.PlayingCard

class FirstTurn(override val hand: Hand) : State {
    override fun drawCard(card: PlayingCard): State {
        hand.add(card)

        if (hand.size == 2) {
            if (hand.isBlackjack()) return Blackjack(hand)
            return Hit(hand)
        }
        return FirstTurn(hand)
    }

    override fun stay(): State {
        throw IllegalStateException()
    }
}
