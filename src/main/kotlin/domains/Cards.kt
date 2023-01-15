package domains

class Cards(cards: List<Card> = listOf()) {
    val values: MutableList<Card> = cards.toMutableList()

    override fun toString(): String {
        return values.joinToString { it.toString() }
    }

    fun addCard(card: Card) {
        values.add(card)
    }

    fun isDrawable(condition: Int): Boolean {
        return values.sumOf { it.pokerNumber.number } < condition
    }

    fun hasAce(): Boolean {
        return values.singleOrNull { it.pokerNumber.isAce() }?.let { true } ?: false
    }
}
