package blackjack

data class Cards(
    private val cards: MutableList<Card> = mutableListOf(),
) {
    override fun toString(): String {
        return cards.joinToString(DELIMITER)
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun calculate(): Int {
        return cards.sortedBy { it.cardNumber.sortOrder }
            .fold(0) { acc, card -> card.calculate(acc) }
    }

    companion object {
        private const val DELIMITER = ", "
    }
}
