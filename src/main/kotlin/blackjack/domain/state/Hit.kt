package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

class Hit(private val hand: Hand) : Running(hand) {
    override fun draw(card: Card): State {
        hand.addCard(card)
        return when {
            hand.isBlackjack() -> Blackjack(hand)
            hand.isBust() -> Bust(hand)
            else -> this
        }
    }

    override fun stay(): State {
        return Stay(hand)
    }
}
