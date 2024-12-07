package blackjack.step2.domain

object ScoreCalculator {
    const val BLACKJACK_SCORE = 21

    fun calculate(cards: Cards): Int {
        val totalScore = cards.all.sumOf { it.number.score }
        val aceCount = cards.all.count { it.number == CardNumber.ACE }

        return calculateBestScoreWithAces(totalScore, aceCount)
    }

    private fun calculateBestScoreWithAces(
        score: Int,
        aceCount: Int,
    ): Int {
        var bestScore = score
        repeat(aceCount) {
            if (bestScore + 10 <= BLACKJACK_SCORE) { // ACE의 대체 점수(11)를 추가
                bestScore += 10
            }
        }
        return bestScore
    }
}
