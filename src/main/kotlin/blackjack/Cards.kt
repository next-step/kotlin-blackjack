package blackjack

class Cards(private val values: List<Card> = listOf()) {
    fun add(card: Card): Cards {
        return Cards(this.values.plus(card))
    }

    fun size(): Int {
        return this.values.size
    }

    fun totalScore(): Score {
        var totalScore: Score = cardScores().totalScore()
        repeat(this.aceCount()) {
            if (totalScore.isLessThan(Score.ACE_BONUS_SCORE_BASE_LINE)) {
                totalScore += Score.ACE_BONUS_SCORE
            }
        }
        return totalScore
    }

    private fun aceCount(): Int {
        return this.values.count { it.number == CardNumber.ACE }
    }

    private fun cardScores() = Scores(this.values.map { it.number.score })

    fun toList(): List<Card> {
        return this.values
    }
}
