package blackjack.domain.participant

import blackjack.domain.PlayerHand
import blackjack.domain.card.Card

sealed class Participant(
    val name: String,
    protected var hand: PlayerHand
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
