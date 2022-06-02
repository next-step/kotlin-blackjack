package blackjack

import java.lang.Integer.max

class ResultCalculator {
    var result = 0

    fun calculateRecursive(cards: List<Card>, idx: Int = 0, sum: Int = 0): Int {
        if (idx == cards.size) {
            val candidate = if (sum > TARGET_NUMBER) {
                0
            } else {
                sum
            }

            result = max(candidate, result)

            return result
        }

        val card = cards[idx]

        if (card.cardNumber.isMultiple) {
            card.cardNumber.multiNumbers?.forEach {
                calculateRecursive(cards, idx + 1, sum + it)
            }
        } else {
            calculateRecursive(cards, idx + 1, sum + card.cardNumber.number)
        }

        return result
    }

    companion object {
        private const val TARGET_NUMBER = 21
    }
}
