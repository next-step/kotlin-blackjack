package blackjack

class Cards(
    vararg cards: String,
) {
    private val cards = cards.toList()

    fun sum(): Int {
        return cards.sumOf { cardValue(it) }
    }

    private fun cardValue(card: String): Int {
        if (card.equals("A")) {
            return 11
        }
        return if (isCharacterCard(card)) 10 else card.toInt()
    }

    private fun isCharacterCard(card: String): Boolean {
        return card.equals("K") || card.equals("J") || card.equals("Q")
    }
}
