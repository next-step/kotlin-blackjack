package blackjack.domain

class Player(private val name: String, cards: List<Card> = emptyList()) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    fun getName(): String {
        return name
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun getCards(): List<Card> {
        return _cards.toList()
    }
}
