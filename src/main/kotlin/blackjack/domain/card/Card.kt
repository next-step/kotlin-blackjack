package blackjack.domain.card

data class Card(
    val suit: Suit,
    val denomination: Denomination
) : Comparable<Card> {

    fun calculateScore(otherScore: Score): Score {
        return denomination.calculateScore(otherScore)
    }

    override fun compareTo(other: Card) = denomination.compareTo(other.denomination)
}
