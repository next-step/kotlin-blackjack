package step2.domain

data class CurrentScore(
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

    fun getResultScore(): Int {
        return if (maxScore > BURST_SCORE) minScore else maxScore
    }

    companion object {
        private const val BURST_SCORE = 21
    }
}
