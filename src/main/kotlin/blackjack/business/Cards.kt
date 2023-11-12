package blackjack.business

class Cards {

    private val _cards = mutableListOf<Card>()

    init {
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                _cards.add(Card(suit, rank))
            }
        }
    }

    val cards: List<Card>
        get() = _cards.toList()

    fun draw(): Card {
        return _cards.random().also {
            _cards.remove(it)
        }
    }
}
