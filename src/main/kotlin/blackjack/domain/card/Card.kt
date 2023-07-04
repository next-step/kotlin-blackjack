package blackjack.domain.card

data class Card(
    val denomination: Denomination,
    val suit: Suit
) {
    override fun toString(): String {
        return "${denomination.title}${suit.title}"
    }

    fun getScore() = denomination.getScore()

    fun getScore(accumulatedScore: Int) = denomination.getScore(accumulatedScore)
}
