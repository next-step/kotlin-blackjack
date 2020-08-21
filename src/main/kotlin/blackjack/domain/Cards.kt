package blackjack.domain

data class Cards(private val _states: Set<Card>) {
    val states: Set<Card>
        get() = _states.toSet()

    constructor(vararg cards: Card) : this(cards.toSet())

    val size: Int
        get() = states.size

    fun sumOfScores(): Int {
        val sum = states.map { it.score() }.sumBy { it }
        return CardScore.sumWithAce(sum, hasAce())
    }

    private fun hasAce() = states.any { it.isAce() }

    override fun toString(): String {
        return states.joinToString { it.toString() }
    }
}
