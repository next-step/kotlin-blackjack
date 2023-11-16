package blackjack.domain

class Cards {
    private val cards: MutableList<Card> = mutableListOf()

    fun add(card: Card) {
        cards.add(card)
    }

    fun get(): List<Card> {
        return cards
    }

    fun calculateScore(useAceAs11: Boolean = false): Int {
        val sum = cards.sumOf { it.denomination.value }

        if (hasAce() && useAceAs11) {
            return sum + 10
        }
        return sum
    }

    private fun hasAce(): Boolean {
        return cards.find { it.isAce() } != null
    }
}
