package balckjac.domain

import balckjac.domain.GameRule.BLACKJACK_SCORE

object ScoreCalculator {

    private fun aceScore(total: Int): Int {
        return if (total + (Denomination.ACE.extraScore) <= BLACKJACK_SCORE)
            Denomination.ACE.extraScore
        else Denomination.ACE.score
    }

    fun calculate(cards: List<Card>): Int {
        val hasAce = cards.firstOrNull() { it.denomination == Denomination.ACE }

        val denominations = cards
            .filter { it.denomination.isSingleScore }
            .map { it.denomination }

        val total = denominations.sumOf {
            it.score
        }

        return if (hasAce != null) {
            total + aceScore(total)
        } else total
    }
}
