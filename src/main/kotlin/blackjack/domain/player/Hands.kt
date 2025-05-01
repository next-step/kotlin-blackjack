package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber

class Hands(
    val cards: List<Card> = emptyList(),
) {
    val initialized
        get() = cards.size >= 2

    val size: Int
        get() = cards.size

    infix operator fun plus(card: Card) = Hands(cards + card)

    fun isBust(): Boolean = calculateScore() > BLACKJACK_SCORE

    fun calculateScore(): Int {
        val sum = cards.sumOf { it.number.value }

        return when {
            !hasAce() -> sum
            sum + ACE_ADD_SCORE > BLACKJACK_SCORE -> sum
            else -> sum + ACE_ADD_SCORE
        }
    }

    fun hasAce() = cards.map { it.number }.contains(CardNumber.ACE)

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val ACE_ADD_SCORE = 10
    }
}
