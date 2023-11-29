package blackjack.domain

class CardList(private val _cards: MutableList<Card> = mutableListOf()) {
    val cards: List<Card>
        get() = _cards

    fun addCard(newCard: Card) {
        _cards.add(newCard)
    }

    fun addCard(newCards: List<Card>) {
        _cards.addAll(newCards)
    }

    fun hasAce(): Boolean {
        return _cards.any(Card::isAce)
    }
}
