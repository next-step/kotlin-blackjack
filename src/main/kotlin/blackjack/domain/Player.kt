package blackjack.domain

class Player(val name: String, private val _cards: MutableList<Card> = mutableListOf()) {
    val cards: List<Card>
        get() = _cards

    fun score(): Int {
        return _cards.sumOf { it.score() }
    }

    fun addCard(newCard: Card) {
        _cards.add(newCard)
    }

    fun addCard(newCards: List<Card>) {
        _cards.addAll(newCards)
    }
}
