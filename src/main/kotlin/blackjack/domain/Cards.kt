package blackjack.domain

class Cards(private val cards: MutableList<Card> = mutableListOf()) {
    companion object {
        private const val BLACKJACK = 21
        private const val ACE_VALUE_DIFFERENCE = 10
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun getTotalValue(): Int {
        val total = cards.sumOf { it.value() }
        val hasAce = cards.any { it.rank == Rank.ACE }
        return if (hasAce && total > BLACKJACK) total - ACE_VALUE_DIFFERENCE else total
    }

    fun asList(): List<Card> {
        return cards.toList()
    }
}
