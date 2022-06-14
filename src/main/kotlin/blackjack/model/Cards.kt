package blackjack.model

class Cards(
    private val cards: List<Card> = emptyList(),
) {
    val size = cards.size

    fun containsAll(cards: List<Card>) = this.cards.containsAll(cards)
}
