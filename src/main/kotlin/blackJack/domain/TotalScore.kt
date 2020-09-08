package blackJack.domain

object TotalScore {
    fun get(hands: Hands): Int {
        val totalScore = hands.getCardsScore()
        if (hands.hasAce()) {
            return calculate(totalScore)
        }
        return totalScore
    }

    private fun calculate(score: Int): Int {
        if (score <= BUST_SCORE) {
            return score + ACE_SCORE
        }
        return score
    }

    private const val ACE_SCORE = 10
    private const val BUST_SCORE = 11
}
