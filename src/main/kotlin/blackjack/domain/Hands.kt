package blackjack.domain

class Hands {
    private val cards: MutableList<Card> = mutableListOf()

    val size: Int
        get() = cards.size

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
}
