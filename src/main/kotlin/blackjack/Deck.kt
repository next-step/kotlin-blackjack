package blackjack

data class Deck(
    private val _cards: MutableList<Card> = CardSuit.entries.flatMap { suit ->
        CARD_RANGE.map { Card(CardNumber(it), suit) }
    }.shuffled().toMutableList()
) {
    val cards: List<Card>
        get() = _cards.toList()

    override fun toString(): String {
        return _cards.joinToString("\n")
    }

    companion object {
        private const val NUMBER_OF_CARDS = 52
        private val CARD_RANGE = 1..13
    }
}