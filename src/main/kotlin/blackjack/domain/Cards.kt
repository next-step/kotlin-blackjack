package blackjack.domain

class Cards(vararg elements: Card) {
    private val _elements = elements.toMutableList()
    val elements: List<Card>
        get() = _elements.map { it.copy() }

    val score: Score
        get() {
            val firstCard = _elements[0]
            val otherCards = _elements.subList(1, _elements.size)
            return firstCard.calculateScore(otherCards)
        }

    init {
        require(this._elements.size == CARDS_MIN_COUNT)
        require(this._elements.distinct().count() == CARDS_MIN_COUNT)
    }

    fun add(card: Card) {
        _elements.add(card)
    }

    companion object {
        private const val CARDS_MIN_COUNT = 2
    }
}
