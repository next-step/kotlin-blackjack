package blackJack.domain

object TotalScore {
    fun getScore(cards: List<Card>): Int {
        val cardScores = cards.map { it.denomination.number }
        if (hasAce(cardScores)) {
            return getHasAceScore(cardScores.sum())
        }
        return cardScores.sum()
    }

    private fun hasAce(cardScores: List<Int>): Boolean = cardScores.contains(ACE)

    private fun getHasAceScore(totalScore: Int): Int {
        if (totalScore <= BUST_SCORE) {
            return totalScore + ACE_SCORE
        }
        return totalScore
    }

    private const val BUST_SCORE = 11
    private const val ACE_SCORE = 10
    private const val ACE = 1
}
