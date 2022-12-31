package blackjack.domain

import blackjack.domain.card.Card

class Hand(val cards: List<Card>) {
    val score: Int
        get() {
            val sum = cards.sumOf { it.score }

            if (isSoft(sum)) {
                return sum + ACE_ADDITIONAL_SCORE
            }

            return sum
        }

    val isBust: Boolean = score > BLACKJACK_SCORE
    val isBlackjack: Boolean = score == BLACKJACK_SCORE

    constructor(vararg cards: Card) : this(cards.toList())

    private fun isSoft(sum: Int): Boolean = cards.any { it.isAce } && sum + ACE_ADDITIONAL_SCORE <= BLACKJACK_SCORE

    companion object {
        private const val ACE_ADDITIONAL_SCORE = 10
        private const val BLACKJACK_SCORE = 21
    }
}
