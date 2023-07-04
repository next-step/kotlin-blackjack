package blackjack.player

import blackjack.card.Card

open class Player(
    val name: String,
    val bettingMoney: Int = 0,
    private val hand: Hand = Hand(),
    private var status: Status = Status.HIT
) {
    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun updateStatus(newStatus: Status) {
        status = newStatus
    }

    val currentStatus: Status
        get() = status

    val handSize: Int
        get() = hand.size

    val displayHand: String
        get() = hand.displayCards

    val totalValue: Int
        get() = ScoreCalculator.calculateScore(hand)
}
