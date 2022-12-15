package blackjack.service

import blackjack.model.Denomination.ACE
import blackjack.model.Player
import blackjack.model.Player.Companion.BLACKJACK_SCORE
import kotlin.math.max

object FinalScoreCalculator {
    private const val FAIL_SCORE = 0
    private const val INDEX_INCREMENT = 1

    fun finalScoreOf(player: Player): Int {
        return calculate(player)
    }

    private fun calculate(cards: Player, index: Int = 0, accumulator: Int = 0): Int {
        if (accumulator > BLACKJACK_SCORE) {
            return FAIL_SCORE
        }
        if (index == cards.size) {
            return accumulator
        }

        return handleDfsBranch(cards, index, accumulator)
    }

    private fun handleDfsBranch(cards: Player, index: Int, accumulator: Int): Int {
        if (!cards[index].isAce()) {
            return calculate(cards, index + INDEX_INCREMENT, accumulator + cards[index].getScore())
        }

        return max(
            calculate(cards, index + INDEX_INCREMENT, accumulator + ACE.score),
            calculate(cards, index + INDEX_INCREMENT, accumulator + ACE.specialScore!!)
        )
    }
}
