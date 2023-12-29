package blackjack.domain

const val BLACKJACK_SCORE = 21

class Cards (
    cards: List<Card>,
) {
    private val cards = cards.toMutableList()
    val values
        get() = cards.toList()

    constructor(vararg cards: Card): this(cards.toList())

    fun sum(): Int {
        if (BLACKJACK_SCORE < sumOfMaximum()) {
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
}
