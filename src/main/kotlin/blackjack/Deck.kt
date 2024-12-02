package blackjack

data class Deck(
    private val _cards: MutableList<Card> = CardSuit.entries.flatMap { suit ->
        CardNumber.entries.map { Card(it, suit) }
    }.shuffled().toMutableList()
) {
    val cards: List<Card>
        get() = _cards.toList()

    init {
        require(_cards.size == NUMBER_OF_CARDS) { "카드의 수가 52장이 아닙니다." }
    }

    fun deal(): List<Card> {
        val cards = _cards.take(DEAL_CARD_COUNT)
        _cards.removeAll(cards)
        return cards
    }

    override fun toString(): String {
        return _cards.joinToString("\n")
    }

    companion object {
        private const val NUMBER_OF_CARDS = 52
        private val CARD_RANGE = 1..13
        private const val DEAL_CARD_COUNT = 2
    }
}