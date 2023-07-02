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

    val names: String
        get() {
            return cards.joinToString { card -> card.name }
        }

    fun addCard(card: Card): Cards {
        val list = mutableListOf<Card>()
        list.addAll(cards)
        list.add(card)
        return Cards(list)
    }

    private fun isHaveAce(): Boolean {
        return cards.any { it.isAce() }
    }

    private fun isCalculatePlusTenPossible(sum: Int): Boolean {
        return sum + 10 <= BLACKJACK_VALUE
    }
}
