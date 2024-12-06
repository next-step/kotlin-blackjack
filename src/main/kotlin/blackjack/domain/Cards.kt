package blackjack.domain

class Cards(private val cards: MutableList<Card> = mutableListOf()) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun getTotalValue(): Int {
        val total = cards.sumOf { it.getValue() }
        val hasAce = cards.any { it.rank == "Ace" }
        return if (hasAce && total > 21) total - 10 else total
    }

    fun asList(): List<Card> {
        return cards.toList()
    }
}
