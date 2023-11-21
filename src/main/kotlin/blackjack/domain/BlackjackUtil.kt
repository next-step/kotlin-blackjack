package blackjack.domain

import blackjack.domain.CardNumberInfo.Companion.ACE_UPPER_SCORE

object BlackjackUtil {
    const val INITIAL_CARD_COUNT = 2
    const val BLACKJACK_SCORE = 21

    fun computeScore(cards: List<Card>): Pair<Int, Int> {
        val minScore = cards.sumOf { it.num.score }
        val maxScore = minScore.minus(1).plus(ACE_UPPER_SCORE)
        val hasAce = cards.any { it.num == CardNumberInfo.ACE }

        return if (hasAce && maxScore <= BLACKJACK_SCORE) {
            Pair(minScore, maxScore)
        } else {
            Pair(minScore, minScore)
        }
    }
}
