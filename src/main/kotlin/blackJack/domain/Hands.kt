package blackJack.domain

class Hands {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards
        get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }
}
