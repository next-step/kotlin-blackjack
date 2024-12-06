package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

sealed class Running(private val hand: Hand) : State {
    override fun isFinished(): Boolean = false

    override fun hand(): Hand = hand
}

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
