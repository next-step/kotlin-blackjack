package domain

data class Card(
    private val suit: Suit,
    private val denomination: Denomination
) {

    fun calculateScore(otherScore: Score): Score {
        return denomination.calculateScore(otherScore)
    }
}
