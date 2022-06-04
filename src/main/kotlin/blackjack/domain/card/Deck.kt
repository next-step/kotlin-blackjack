package blackjack.domain.card


class Deck(cards: List<Card>) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards get() = _cards.toList()
    fun draw(): Card = _cards.removeAt(0)
    fun count() = _cards.count()
}


