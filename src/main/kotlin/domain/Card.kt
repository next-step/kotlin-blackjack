package domain

data class Card(
    private val suit: Suit,
    private val denomination: Denomination
) : Comparable<Card> {

    fun calculateScore(otherScore: Score): Score {
        return denomination.calculateScore(otherScore)
    }

    override fun compareTo(other: Card) = denomination.compareTo(other.denomination)
}
