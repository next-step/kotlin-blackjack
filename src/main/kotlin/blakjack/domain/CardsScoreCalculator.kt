package blakjack.domain

import blakjack.domain.CardRank.Companion.ACE_SCORE_DIFF

object CardsScoreCalculator {
    fun sum(cards: Cards): Int {
        var sum = sumWithAceAsOne(cards)

        repeat(getAceCount(cards)) {
            if (isBust(sum + ACE_SCORE_DIFF)) return@repeat
            sum += ACE_SCORE_DIFF
        }

        return sum
    }

    fun sumWithAceAsOne(cards: Cards): Int {
        return cards.values.sumOf { it.rank.value }
    }

    private fun isBust(score: Int): Boolean {
        return score > BLACKJACK_SCORE
    }

    private fun getAceCount(cards: Cards): Int {
        return cards.values.count { it.rank.isAce() }
    }
}
