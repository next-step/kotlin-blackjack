package balckjac.domain

import balckjac.domain.GameRule.ACE_EXTRA_SCORE
import balckjac.domain.GameRule.BLACKJACK_SCORE

object ScoreCalculator {

    fun calculate(cards: List<Card>): Int {
        val denominations = cards.map { it.denomination }

        var total = denominations.sumOf { it.score }

        total += if (denominations.contains(Denomination.ACE) && total + ACE_EXTRA_SCORE <= BLACKJACK_SCORE)
            ACE_EXTRA_SCORE
        else 0

        return total
    }

}
