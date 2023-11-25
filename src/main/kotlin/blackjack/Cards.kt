package blackjack

class Cards(
    vararg cards: Card,
) {
    private val cards = cards.toList()

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

    companion object {
        private const val BLACKJACK = 21
    }
}
