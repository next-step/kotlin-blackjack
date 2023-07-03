package blackjack.domain

import blackjack.domain.BlackjackGame.Companion.BLACKJACK_VALUE

data class Cards(val cards: List<Card>) {
    val value: Int
        get() {
            val sum = cards.sumOf(Card::value)
            if (isHaveAce() && isCalculatePlusTenPossible(sum)) {
                return sum + 10
            }
            return sum
        }

    fun addCard(card: Card): Cards {
        return Cards(cards.plus(card))
    }

    fun size(): Int {
        return cards.size
    }

    private fun isHaveAce(): Boolean {
        return cards.any { it.isAce() }
    }

    private fun isCalculatePlusTenPossible(sum: Int): Boolean {
        return sum + 10 <= BLACKJACK_VALUE
    }
}
