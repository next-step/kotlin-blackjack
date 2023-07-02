package blackjack.domain

class Cards(val cards: List<Card>) {
    val value: Int
        get() {
            val sum = cards.sumOf(Card::value)
            if (isHaveAce() && isCalculatePlusTenPossible(sum)) {
                return sum + 10
            }
            return sum
        }

    val names: String
        get() {
            return cards.joinToString { card -> card.name }
        }

    private fun isHaveAce(): Boolean {
        return cards.any { it.isAce() }
    }

    private fun isCalculatePlusTenPossible(sum: Int): Boolean {
        return sum + 10 <= 21
    }
}
