package domain

object BlackjackRules {
    const val MAXIMUM_SCORE = 21
    const val DEALER_HIT_THRESHOLD = 17
    const val ACE_HIGH_TO_LOW_DIFFERENCE = 10

    fun isBlackjack(cards: List<Card>): Boolean {
        if (cards.size != 2) {
            return false
        }
        val totalScore = cards.sumOf { it.rank.value }
        return totalScore == MAXIMUM_SCORE
    }
}
