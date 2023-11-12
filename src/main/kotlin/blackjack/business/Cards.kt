package blackjack.business

class Cards {
    fun draw(): Card {
        return _cards.random().also {
            _cards.remove(it)
        }
    }

    private val _cards = mutableListOf<Card>()

    val cards: List<Card>
        get() = _cards.toList()

    init {
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                _cards.add(Card(suit, rank))
            }
        }
    }
}
