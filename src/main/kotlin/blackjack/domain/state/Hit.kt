package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card

class Hit(override val hand: Hand) : State {
    override fun draw(card: Card): State {
        val newHand = Hand(hand.cards + card)

        return when {
            newHand.isBust -> Bust(newHand)
            newHand.isBlackjack -> Blackjack(newHand)
            else -> Hit(newHand)
        }
    }
}
