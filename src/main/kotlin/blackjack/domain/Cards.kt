package blackjack.domain

data class Cards(
    val values: List<Card>
) {
    operator fun plus(card: Card): Cards = Cards(this.values + card)

    operator fun plus(cards: List<Card>): Cards = Cards(this.values + cards)

    operator fun plus(cards: Cards): Cards = Cards(this.values + cards.values)

    fun sumScores(): Int {
        val totalScore = values.sumBy { it.denomination.score }
        return if (values.hasAce()) sumScoresWithAce(totalScore)
        else totalScore
    }

    private fun List<Card>.hasAce(): Boolean {
        val aceCard = this.find { it.denomination == Denomination.ACE }
        return aceCard != null
    }

    private fun sumScoresWithAce(totalScore: Int): Int {
        val totalScoreWithAceBonus = totalScore + Denomination.MAX_SCORE
        return if (totalScoreWithAceBonus > BLACK_JACK_SCORE) totalScore
        else totalScoreWithAceBonus
    }

    override fun toString(): String {
        return values.joinToString(", ")
    }

    companion object {
        const val BLACK_JACK_SCORE = 21

        fun empty() = Cards(emptyList())

        fun denominationsOf(vararg values: String) =
            Cards(values.map { Card.denominationOf(it) })
    }
}
