package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card

object FirstTurn {
    fun draw(firstCard: Card, secondCard: Card): State {
        val hand = Hand(firstCard, secondCard)

        if (hand.isBlackjack) {
            return Blackjack(hand)
        }

        return Hit(hand)
    }
}
