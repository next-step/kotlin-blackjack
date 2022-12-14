package blackjack.service

import blackjack.model.Card
import blackjack.model.Denomination.ACE
import kotlin.math.max

object FinalScoreCalculator {
    private const val FAIL_SCORE = 0
    private const val FAIL_THRESHOLD = 21
    private const val INDEX_INCREMENT = 1

    fun calculate(cards: List<Card>, index: Int = 0, accumulator: Int = 0): Int {
        if (accumulator > FAIL_THRESHOLD) {
            return FAIL_SCORE
        }
        if (index == cards.size) {
            return accumulator
        }

        return handleDfsBranch(cards, index, accumulator)
    }

    private fun handleDfsBranch(cards: List<Card>, index: Int, accumulator: Int): Int {
        if (!cards[index].isAce()) {
            return calculate(cards, index + INDEX_INCREMENT, accumulator + cards[index].getScore())
        }

        return max(
            calculate(cards, index + INDEX_INCREMENT, accumulator + ACE.score),
            calculate(cards, index + INDEX_INCREMENT, accumulator + ACE.specialScore!!)
        )
    }
}
