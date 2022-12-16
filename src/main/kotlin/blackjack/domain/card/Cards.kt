package blackjack.domain.card

data class Cards(
    val cards: MutableList<Card> = mutableListOf(),
) {
    override fun toString(): String {
        return cards.joinToString(DELIMITER)
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun pickFirst(): Card {
        return cards.removeFirst()
    }

    fun calculate(): Int {
        return cards.sortedBy { it.cardNumber.sortOrder }
            .fold(0) { acc, card -> card.calculate(acc) }
    }

    companion object {
        private const val DELIMITER = ", "
    }
}
