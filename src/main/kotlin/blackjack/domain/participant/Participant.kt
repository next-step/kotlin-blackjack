package blackjack.domain.participant

import blackjack.domain.Hand
import blackjack.domain.card.Card

sealed class Participant(
    val name: String,
    protected var hand: Hand
) {
    fun isBust() = hand.isBust()

    fun isBlackJack() = hand.isBlackJack()

    fun score() = hand.score

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
