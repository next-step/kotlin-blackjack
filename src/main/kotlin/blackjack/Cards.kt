package blackjack

class Cards(private val values: List<Card> = listOf()) {
    fun add(card: Card): Cards {
        return Cards(this.values.plus(card))
    }

    fun size(): Int {
        return this.values.size
    }

    fun totalScore(): Score {
        return cardScores().totalScore()
    }

    private fun cardScores() = Scores(this.values.map { it.number.score })
}
