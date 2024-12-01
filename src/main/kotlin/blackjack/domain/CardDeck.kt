package blackjack.domain

data class CardDeck(
    private val cards: MutableList<Card>,
) {
    fun pickCard(): Card = cards.removeLast()

    companion object {
        fun from(value: List<Card>): CardDeck = CardDeck(value.toMutableList())
    }
}
