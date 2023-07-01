package blackjack

object CardScoreCalculator {
    private const val BLACKJACK_NUMBER = 21
    fun calculate(cards: List<Card>): Int {
        val aceCardCount = cards.count { it.number == CardNumber.ACE }
        if (aceCardCount == 0) {
            return cards.sumOf { it.number.scores.first() }
        }
        val otherCardScoreSum = cards
            .filter { it.number != CardNumber.ACE }
            .sumOf { it.number.scores.first() }

        val possibleAceScoreSums = calculateAllPossibleAceScores(aceCardCount)

        return getBestTotalScoreSums(possibleAceScoreSums.map { it + otherCardScoreSum })
    }

    private fun calculateAllPossibleAceScores(aceCardCount: Int): List<Int> {
        val possibleAceScoreSums = mutableListOf<Int>()
        for (maxScoreAceCount in 0..aceCardCount) {
            val minScoreAceCount = aceCardCount - maxScoreAceCount
            val sumOfAceScore = CardNumber.ACE.scores.max() * maxScoreAceCount + CardNumber.ACE.scores.min() * minScoreAceCount
            possibleAceScoreSums.add(sumOfAceScore)
        }

        return possibleAceScoreSums
    }

    private fun getBestTotalScoreSums(possibleScoreSums: List<Int>): Int {

        return possibleScoreSums
            .filter { it <= BLACKJACK_NUMBER }
            .maxOrNull()
            ?: return possibleScoreSums.min()
    }
}
