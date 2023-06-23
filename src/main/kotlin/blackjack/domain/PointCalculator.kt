package blackjack.domain

import blackjack.controller.BlackjackGame

object PointCalculator {
    fun calculateUserPoint(deck: Deck): Int? {
        return calculateRecursively(deck, 0, 0)
    }

    private fun calculateRecursively(deck: Deck, index: Int, sum: Int): Int? {
        if (index == deck.size) {
            return sum
        }

        val points = getCardPoints(deck[index])

        for (point in points) {
            return getResult(deck, index, sum, point) ?: continue
        }
        return null
    }

    private fun getCardPoints(card: Card): List<Int> {
        return when (card.cardNumber) {
            is AceCardNumber -> listOf(11, 1)
            is JackQueenKingCardNumber -> listOf(10)
            is NumberCardNumber -> listOf(card.cardNumber.number)
        }
    }

    private fun getResult(deck: Deck, index: Int, sum: Int, point: Int): Int? =
        if (sum + point > BlackjackGame.BLACKJACK_LIMIT) {
            null
        } else {
            calculateRecursively(deck, index + 1, sum + point)
        }
}
