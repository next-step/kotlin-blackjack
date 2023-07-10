package blackjack.domain.card

import blackjack.domain.BlackjackGame.Companion.BLACKJACK_VALUE

data class Cards(val cards: List<Card>) {
    val size: Int
        get() {
            return cards.size
        }

    fun value(): Int {
        val sum = cards.sumOf(Card::value)
        if (isHaveAce() && isCalculatePlusTenPossible(sum)) {
            return sum + 10
        }
        return sum
    }

    fun plusCard(card: Card): Cards {
        return Cards(cards.plus(card))
    }

    private fun isHaveAce(): Boolean {
        return cards.any { it.isAce() }
    }

    private fun isCalculatePlusTenPossible(sum: Int): Boolean {
        return sum + 10 <= BLACKJACK_VALUE
    }
}
