package blackjack

class Cards(
    vararg cards: String,
) {
    private val cards = cards.toList()

    fun sum(): Int {
        val minimum = sumOfMinimum()
        val maximum = sumOfMaximum()

        if (BLACKJACK < maximum) {
            return minimum
        }
        return maximum
    }

    private fun sumOfMaximum(): Int {
        if (cards.any { it.equals("A") }) {
            return sumOfMinimum() + 10
        }
        return sumOfMinimum()
    }

    private fun sumOfMinimum(): Int {
        return cards.sumOf { cardValue(it) }
    }

    private fun cardValue(card: String): Int {
        if (card.equals("A")) {
            return 1
        }
        return if (isCharacterCard(card)) 10 else card.toInt()
    }

    private fun isCharacterCard(card: String): Boolean {
        return card.equals("K") || card.equals("J") || card.equals("Q")
    }

    companion object {
        private const val BLACKJACK = 21
    }
}
