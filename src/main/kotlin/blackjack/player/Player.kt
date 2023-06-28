package blackjack.player

import blackjack.card.Card

class Player(
    val name: String,
    private val hand: Hand = Hand(),
    private var status: Status = Status.WANT
) {
    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun getHandSize(): Int {
        return hand.size()
    }

    fun updateStatus(newStatus: Status) {
        status = newStatus
    }

    fun getStatus(): Status {
        return status
    }

    fun displayHand(): String {
        return hand.displayCards()
    }

    fun getTotalValue(): Int {
        return ScoreCalculator.calculateScore(hand)
    }
}
