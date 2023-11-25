package blackjack

import kotlin.math.abs

class Cards(
    vararg cards: String,
) {
    private val cards = cards.toList()

    fun sum(): Int {
        val maximum = sumOfMaximum()
        val minimum = sumOfMinimum()
        return valueOfCloseToBlackjack(maximum, minimum)
    }

    private fun valueOfCloseToBlackjack(left: Int, right: Int): Int {
        if (abs(BLACKJACK - left) > abs(BLACKJACK - right)) {
            return right
        }
        return left
    }

    private fun sumOfMinimum() = sumOfMaximum() - 10

    private fun sumOfMaximum() = cards.sumOf { cardValue(it) }

    private fun cardValue(card: String): Int {
        if (card.equals("A")) {
            return 11
        }
        return if (isCharacterCard(card)) 10 else card.toInt()
    }

    private fun isCharacterCard(card: String): Boolean {
        return card.equals("K") || card.equals("J") || card.equals("Q")
    }

    companion object {
        private const val BLACKJACK = 21
    }
}
