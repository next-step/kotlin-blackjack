package blackjack.domain

import blackjack.domain.CardNumberInfo.Companion.ACE_UPPER_SCORE

object BlackjackUtil {
    const val INITIAL_CARD_NUM = 2
    const val DEALER_OPEN_CARD_NUM = 1
    private const val BLACKJACK_SCORE = 21

    fun computeScore(hand: Hand): Pair<Int, Int> {
        val minScore = hand.sumOf()
        val maxScore = minScore.minus(1).plus(ACE_UPPER_SCORE)

        return if (hand.hasAce() && maxScore <= BLACKJACK_SCORE) {
            Pair(minScore, maxScore)
        } else {
            Pair(minScore, minScore)
        }
    }

    fun isBust(score: Int): Boolean = score > BLACKJACK_SCORE
}
