package blackjack.domain

class Cards private constructor() {

    private val cards = mutableListOf<Card>()

    constructor(vararg values: String) : this() {
        this.cards.addAll(values.map { Card.denominationOf(it) })
    }

    fun add(card: Card) {
        cards.add(card)
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
        return if (totalScoreWithAceBonus > 21) totalScore
        else totalScoreWithAceBonus
    }

    override fun toString(): String {
        return cards.joinToString(", ")
    }

    companion object {
        fun empty() = Cards()
    }
}
