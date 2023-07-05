package step2.domain

data class CardCurrentScore(
    var minScore: Int = 0,
    var maxScore: Int = 0
) {
    fun calculateScore(cards: Set<Card>) {
        minScore = cards.sumOf { it.score.defaultScore }
        maxScore = cards.sumOf { if (it.score.isAce()) CardScore.ACE_LARGE_SCORE else it.score.defaultScore }
    }

    fun isBurst(): Boolean {
        return minScore > BURST_SCORE && maxScore > BURST_SCORE
    }

    companion object {
        private const val BURST_SCORE = 21
    }
}
