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

        return getBestTotalSums(possibleAceScoreSums, otherCardScoreSum)
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

    private fun getBestTotalSums(possibleAceScoreSums: List<Int>, otherCardScoreSum: Int): Int {
        val ascendingPossibleAceScoreSums = possibleAceScoreSums.sorted()

        var bestTotalSum = 0
        for (aceScoreSum in ascendingPossibleAceScoreSums) {
            val totalSum = aceScoreSum + otherCardScoreSum
            if (totalSum <= BLACKJACK_NUMBER) {
                bestTotalSum = totalSum
                continue
            }
            if (bestTotalSum == 0) {
                bestTotalSum = totalSum
                break
            }
        }
        return bestTotalSum
    }
}
