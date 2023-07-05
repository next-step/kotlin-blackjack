package blackjack.domain

class Player {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun addCards(cards: List<Card>): Boolean = _cards.addAll(cards)
    fun addCard(card: Card): Boolean = _cards.add(card)
}
