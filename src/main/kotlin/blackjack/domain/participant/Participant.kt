package blackjack.domain.participant

import blackjack.domain.Hand
import blackjack.domain.card.Card

sealed class Participant(
    val name: String,
    protected var hand: Hand
) {
    fun isBust() = hand.isBust()
    fun score() = hand.score
    fun cards() = hand.cards

    protected fun addCards(drawCard: () -> List<Card>) {
        hand = hand.add(drawCard())
    }

    protected fun addCard(drawCard: () -> Card) {
        hand = hand.add(drawCard())
    }
}
