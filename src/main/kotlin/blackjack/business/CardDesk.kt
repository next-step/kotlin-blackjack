package blackjack.business

class CardDesk(private val cardSelectionStrategy: CardSelectionStrategy = RandomCardSelectionStrategy()) {

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
        return cardSelectionStrategy.selectCard(_cards).also {
            _cards.remove(it)
        }
    }
}
