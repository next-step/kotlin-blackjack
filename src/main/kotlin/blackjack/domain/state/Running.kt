package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

sealed class Running(val hand: Hand) : State {
    override fun isFinished() = false

    override fun calculateTotal() = hand.calculateBestTotal()

    override fun isBlackjack() = hand.isBlackjack()

    override fun isBust() = hand.isBust()

    override fun getAllCards() = hand.getAllCards()

    override fun hand(): Hand {
        return hand
    }
}

class Hit(hand: Hand) : Running(hand) {
    override fun draw(card: Card): State {
        hand.addCard(card)
        return when {
            hand.isBlackjack() -> Blackjack(hand)
            hand.isBust() -> Bust(hand)
            else -> this
        }
    }

    override fun stay(): State = Stay(hand)
}
