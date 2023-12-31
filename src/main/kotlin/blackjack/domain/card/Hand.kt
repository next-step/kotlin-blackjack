package blackjack.domain.card

data class Hand(
    private val _cards: MutableList<Card> = mutableListOf()
) {
    val cards: List<Card>
        get() = _cards.toList()

    val cardsCount = _cards.size

    val ranks: List<Rank>
        get() = _cards.map { it.rank }

    val isBlackJackCardSize: Boolean = _cards.size == BLACK_JACK_CARD_SIZE

    fun add(card: Card) {
        _cards.add(card)
    }

    companion object {
        private const val BLACK_JACK_CARD_SIZE = 2
    }
}
