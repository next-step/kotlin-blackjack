package blackjack

class PlayerDeck(cards: List<Card> = emptyList()) {

    private val _cards: MutableList<Card> = cards.toMutableList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    val cards: List<Card>
        get() = _cards.toList()
}
