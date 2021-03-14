package blackjack.domain

data class Card(
    private val suit: Suit,
    private val denomination: Denomination
) {
    fun calculateScore(cards: List<Card>): Score {
        return denomination.calculateScore(
            cards.map { it.denomination }
        )
    }
}
