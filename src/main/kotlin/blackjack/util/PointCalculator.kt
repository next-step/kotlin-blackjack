package blackjack.util

import blackjack.BlackjackGame
import blackjack.domain.Deck

object PointCalculator {
    fun calculateUserPoint(deck: Deck): Int? {
        return calculateRecursively(deck, 0, 0)
    }

    private fun calculateRecursively(deck: Deck, index: Int, sum: Int): Int? {
        if (index == deck.size) {
            return sum
        }
        val card = deck[index]
        for (point in card.points) {
            return getResult(deck, index, sum, point) ?: continue
        }
        return null
    }

    private fun getResult(deck: Deck, index: Int, sum: Int, point: Int): Int? =
        if (sum + point > BlackjackGame.BLACKJACK_LIMIT) {
            null
        } else {
            calculateRecursively(deck, index + 1, sum + point)
        }
}
