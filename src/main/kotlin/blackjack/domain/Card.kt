package blackjack.domain

data class Card(
    val suit: Suit,
    val denomination: Denomination
) {
    fun calculateScore(cards: List<Card>): Score {
        return denomination.calculateScore(
            cards.map { it.denomination }
        )
    }
}
