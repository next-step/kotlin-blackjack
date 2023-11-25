package blackjack

class Cards (
    cards: List<Card>,
) {
    private val cards = cards.toMutableList()

    constructor(vararg cards: Card): this(cards.toList())

    fun sum(): Int {
        if (BLACKJACK < sumOfMaximum()) {
            return sumOfMinimum()
        }
        return sumOfMaximum()
    }

    private fun sumOfMaximum(): Int {
        if (cards.any { it.isAce }) {
            return sumOfMinimum() + 10
        }
        return sumOfMinimum()
    }

    private fun sumOfMinimum(): Int {
        return cards.sumOf { it.value }
    }

    fun add(card: Card) {
        cards.add(card)
    }

    companion object {
        private const val BLACKJACK = 21
    }
}
