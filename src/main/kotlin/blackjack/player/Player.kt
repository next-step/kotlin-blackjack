package blackjack.player

import blackjack.card.Card
import blackjack.game.BlackjackGame

open class Player(
    val name: String,
    var bettingMoney: Int = 0,
    private val hand: Hand = Hand(),
    private var status: Status = Status.HIT
) {
    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun updateStatus(newStatus: Status) {
        status = newStatus
    }

    fun updateMoney(money: Int) {
        bettingMoney += money
    }
    val currentStatus: Status
        get() = status

    val handSize: Int
        get() = hand.size

    val displayHand: String
        get() = hand.displayCards

    val isBlackjack: Boolean
        get() = handSize == BLACKJACK_CARD_COUNT && totalValue == BlackjackGame.BLACK_JACK_SCORE

    val isBust: Boolean
        get() = totalValue > BlackjackGame.BLACK_JACK_SCORE

    val totalValue: Int
        get() = ScoreCalculator.calculateScore(hand)

    companion object {
        private const val BLACKJACK_CARD_COUNT = 2
    }
}
