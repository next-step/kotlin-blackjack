package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.hand.Hand

sealed class Participant(
    protected var hand: Hand
) {
    fun isBust() = hand.isBust()

    fun isBlackJack() = hand.isBlackJack()

    fun score() = hand.handScore

    fun cards() = hand.cards

    fun start(drawCard: () -> Card) {
        val cards = List(START_CARD_COUNT) { drawCard() }
        addCards { cards }
    }

    private fun addCards(drawCard: () -> List<Card>) {
        hand = hand.add(drawCard())
    }

    protected fun addCard(drawCard: () -> Card) {
        hand = hand.add(drawCard())
    }

    companion object {
        const val START_CARD_COUNT = 2
    }
}
