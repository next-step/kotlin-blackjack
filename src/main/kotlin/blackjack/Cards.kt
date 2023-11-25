package blackjack

class Cards (
    cards: List<Card>,
) {
    private val cards = cards.toMutableList()
    val values
        get() = cards.toList()

    constructor(vararg cards: Card): this(cards.toList())

    fun sum(): Int {
        if (BLACKJACK < sumOfMaximum()) {
            return sumOfMinimum()
        }
        return sumOfMaximum()
    }

    private fun sumOfMaximum(): Int {
        if (hasAce()) {
            return sumOfMinimum() + 10
        }
        return sumOfMinimum()
    }

    private fun hasAce() = cards.any { it.isAce }

    private fun sumOfMinimum(): Int {
        return cards.sumOf { it.value }
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun isLessThanBlackjack(): Boolean {
        return sum() < BLACKJACK
    }

    companion object {
        private const val BLACKJACK = 21
    }
}
