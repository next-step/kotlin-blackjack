package blackjack.domain

data class Card(
    val suit: Suit,
    val denomination: Denomination
) {
    fun scores(): List<Score> = denomination.scores()
}
