package step2.domain

data class CurrentScore(
    var minScore: Int = 0,
    var maxScore: Int = 0
) {
    fun calculateScore(cards: Set<Card>) {
        minScore = cards.sumOf { it.denomination.defaultScore }
        maxScore = cards.sumOf { if (it.denomination.isAce()) Denomination.ACE_LARGE_SCORE else it.denomination.defaultScore }
    }

    fun isBust(): Boolean {
        return minScore > BURST_SCORE && maxScore > BURST_SCORE
    }

    fun getResultScore(): Int {
        return if (maxScore > BURST_SCORE) minScore else maxScore
    }

    companion object {
        private const val BURST_SCORE = 21
    }
}
