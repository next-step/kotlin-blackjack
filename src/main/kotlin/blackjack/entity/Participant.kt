package blackjack.entity

import kotlin.math.abs

abstract class Participant(val name: String) {
    companion object {
        const val BLACKJACK = 21
    }

    val hand = Hand()

    fun receiveCard(card: Card) {
        hand.addCard(card)
    }

    fun calculateScore(): Int {
        return hand.calculateScore()
    }

    fun isBusted(): Boolean {
        return hand.isBusted()
    }

    fun closeToBlackjack(score: Int): Int {
        return abs(BLACKJACK - score)
    }

    abstract fun calculateResult(score: ComparisonScore): GameResult
}
