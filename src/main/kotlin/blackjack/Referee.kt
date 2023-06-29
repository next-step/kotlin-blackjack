package blackjack

object Referee {
    fun evaluate(cards: List<Card>): Evaluation {
        val aceCardCount = cards.count { it.number == CardNumber.ACE }
        if (aceCardCount == 0) {
            val scoreSum = cards.sumOf { it.number.scores.first() }
            return Evaluation.from(scoreSum)
        }
        val otherCardScoreSum = cards
            .filter { it.number != CardNumber.ACE }
            .sumOf { it.number.scores.first() }

        val minimumScoreSum = otherCardScoreSum + CardNumber.ACE.scores.min() * aceCardCount
        val minimumSumEvaluation = Evaluation.from(minimumScoreSum)
        if (minimumSumEvaluation != Evaluation.CONTINUE) {
            return minimumSumEvaluation
        }

        val maximumScoreSum = otherCardScoreSum + CardNumber.ACE.scores.min() * (aceCardCount - 1) + CardNumber.ACE.scores.max()
        val maximumSumEvaluation = Evaluation.from(maximumScoreSum)
        if (maximumSumEvaluation != Evaluation.WIN) {
            return Evaluation.CONTINUE
        }
        return Evaluation.WIN
    }
}
