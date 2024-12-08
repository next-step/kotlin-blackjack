package blackjack.step2.domain

object ScoreCalculator {
    const val BLACKJACK_SCORE = 21

    fun calculate(cards: Cards): Int {
        return cards.all.fold(0) { totalScore, card ->
            val currentScore = totalScore + card.number.score
            totalScore + card.number.calculateScore(currentScore)
        }
    }
}
