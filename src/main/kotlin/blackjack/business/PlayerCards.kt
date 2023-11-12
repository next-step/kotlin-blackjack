package blackjack.business

class PlayerCards {
    val size: Int
        get() = _cards.size
    private val _cards: MutableList<Card> = mutableListOf()

    fun add(card: Card) {
        _cards.add(card)
    }

    operator fun get(i: Int): Card {
        return _cards[i]
    }
}
