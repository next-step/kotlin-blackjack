package blackjack.domain

class Hands {
    private val cards: MutableList<Card> = mutableListOf()

    val size: Int
        get() = cards.size

    val isBust: Boolean
        get() = calculateTotalValue() > THRESHOLD_VALUE

    val isBlackjack: Boolean
        get() = size == BLACKJACK_SIZE && calculateTotalValue() == THRESHOLD_VALUE

    fun add(card: Card) {
        cards.add(card)
    }

    fun calculateTotalValue(): Int {
        val ranks = cards.map(Card::rank)
        return Rank.calculateTotalValue(ranks)
    }

    override fun toString(): String {
        return cards.joinToString(", ")
    }

    companion object {
        private const val THRESHOLD_VALUE = 21
        private const val BLACKJACK_SIZE = 2
    }
}
