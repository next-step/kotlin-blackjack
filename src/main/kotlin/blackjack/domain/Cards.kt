package blackjack.domain

class Cards(vararg values: String) {

    private val cards = values.map { Card.denominationOf(it) }.toMutableList()

    fun add(card: Card) {
        cards.add(card)
    }

    fun get(): List<Card> {
        return cards.toList()
    }

    fun sumScores(): Int {
        val totalScore = cards.sumBy { it.denomination.score }
        return if (cards.hasAce()) sumScoresWithAce(totalScore)
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
        return cards.joinToString(", ")
    }

    companion object {
        const val BLACK_JACK_SCORE = 21

        fun empty() = Cards()
    }
}
