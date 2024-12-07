package blackjack.domain

data class Cards(val values: List<Card>) {
    val score: Int
        get() = calculateScore()

    fun isScoreLowerThanLimit(): Boolean {
        return score < BLACKJACK_SCORE_LIMIT
    }

    fun add(card: Card): Cards {
        return Cards(values + card)
    }

    private fun calculateScore(): Int {
        val totalScore = values.sumOf { it.score }
        var aceCount = values.count { it.isAce() }

        var adjustedScore = totalScore
        while (adjustedScore > BLACKJACK_SCORE_LIMIT && aceCount > 0) {
            adjustedScore -= ACE_SCORE_DIFFERENCE
            aceCount--
        }
        return adjustedScore
    }

    companion object {
        private const val BLACKJACK_SCORE_LIMIT = 21
        private const val ACE_SCORE_DIFFERENCE = 10
    }
}
