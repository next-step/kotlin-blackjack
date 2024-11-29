package blackjack

class Hand(initialCards: List<Card> = emptyList()) {
    private val _cards = initialCards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    fun add(newCard: Card) {
        _cards.add(newCard)
    }
}
