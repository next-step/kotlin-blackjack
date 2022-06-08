package blackjack.domain

import java.lang.Integer.max

class ResultCalculator {
    private var result = 0

    fun calculateRecursive(cards: List<Card>, idx: Int = 0, sum: Int = 0): Int {
        if (idx == cards.size) {
            checkResultCandidate(sum)
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

    private fun checkResultCandidate(sum: Int) {
        val candidate = if (sum > TARGET_NUMBER) {
            0
        } else {
            sum
        }

        result = max(candidate, result)
    }

    companion object {
        private const val TARGET_NUMBER = 21
    }
}
