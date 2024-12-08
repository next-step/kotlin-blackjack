package blackjack.domain

object ScoreCalculator {
    fun calculate(cards: List<Card>): Int {
        val sumOfNotAceScore = cards.filter { card -> card.isAce.not() }
            .sumOf { card -> card.scores.first() }

        return cards.filter { card -> card.isAce }
            .fold(sumOfNotAceScore) { accumulatedScore, card ->
                accumulatedScore + determineAceScore(accumulatedScore, card)
            }
    }

    private fun determineAceScore(accumulatedScore: Int, card: Card): Int {
        if (accumulatedScore >= BlackJackRule.WIN_SCORE) {
            return card.scores.min()
        }

        return card.scores
            .filter { score -> score + accumulatedScore <= BlackJackRule.WIN_SCORE }
            .max()
    }
}