package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card

class Hit(override val hand: Hand) : State {
    override fun draw(card: Card): State {
        val newHand = Hand(hand.cards + card)

        if (newHand.isBust) {
            return Bust(newHand)
        }

        return Hit(newHand)
    }

    fun toStay(): Stay {
        return Stay(hand)
    }
}
