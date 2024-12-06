package blackjack

data class Cards(
    private val _cards: MutableList<Card> = mutableListOf()
) {
    override fun toString(): String {
        return _cards.joinToString(", ")
    }

    fun isBust(): Boolean {
        return sum() >= BLACKJACK
    }

    fun sum() = _cards.sumOf { it.number.score }

    fun add(card: Card) {
        _cards.add(card)
    }

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }

    companion object {
        private const val BLACKJACK = 21
    }
}