package blackJack.domain

object TotalScore {
    fun getScore(cards: List<Int>): Int {
        if (hasAce(cards)) {
            return getHasAceScore(cards.sum())
        }
        return cards.sum()
    }

    private fun hasAce(cards: List<Int>): Boolean = cards.contains(ACE)

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
