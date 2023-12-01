package domain

object BlackjackRules {
    const val MAXIMUM_SCORE = 21
    const val DEALER_HIT_THRESHOLD = 17
    const val ACE_HIGH_TO_LOW_DIFFERENCE = 10

    fun isBlackjack(cards: List<Card>): Boolean {
        return cards.size == 2 && cards.sumOf { it.rank.value } == MAXIMUM_SCORE
    }

    fun isBust(score: Int): Boolean {
        return score > MAXIMUM_SCORE
    }
}
