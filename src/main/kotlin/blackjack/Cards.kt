package blackjack

class Cards(
    private val cards: List<Card>,
) {
    val size = cards.size

    fun containsAll(cards: List<Card>) = this.cards.containsAll(cards)
}
