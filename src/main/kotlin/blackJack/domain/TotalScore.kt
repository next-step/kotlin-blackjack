package blackJack.domain

object TotalScore {
    fun get(hands: List<Card>): Int {
        val totalScore = hands.sumBy { it.getNumber() }
        if (hasAce(hands)) {
            return calculate(totalScore)
        }
        return totalScore
    }

    private fun hasAce(hands: List<Card>): Boolean = hands.any { it.getNumber() == ACE }

    private fun calculate(score: Int): Int {
        if (score <= BUST_SCORE) {
            return score + ACE_SCORE
        }
        return score
    }

    private const val ACE = 1
    private const val ACE_SCORE = 10
    private const val BUST_SCORE = 11
}
