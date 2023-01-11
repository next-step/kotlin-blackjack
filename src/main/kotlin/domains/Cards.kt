package domains

@JvmInline
value class Cards(private val _cards: MutableList<Card> = mutableListOf()) {
    val values: List<Card>
        get() = _cards

    override fun toString(): String {
        return values.joinToString { it.toString() }
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun isDrawable(condition: Int): Boolean {
        return values.sumOf { it.pokerNumber.number } < condition
    }

    fun hasAce(): Boolean {
        return values.singleOrNull { it.pokerNumber.isAce() }?.let { true } ?: false
    }
}
