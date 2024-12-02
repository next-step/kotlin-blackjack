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

    override fun toString(): String {
        return _cards.joinToString("\n")
    }

    fun pick(): Card {
        return this.pick(DEFAULT_PICK_COUNT).first()
    }

    fun pick(count: Int): List<Card> {
        require(count <= _cards.size) { "카드의 수가 부족합니다." }
        val card = _cards.take(count)
        _cards.removeAll(card)
        return card
    }

    companion object {
        private const val NUMBER_OF_CARDS = 52
        private const val DEFAULT_PICK_COUNT = 1
    }
}