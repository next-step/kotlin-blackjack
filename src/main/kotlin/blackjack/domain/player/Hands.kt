package blackjack.domain.player

import blackjack.domain.card.Card

class Hands(
    val cards: List<Card> = emptyList(),
) {
    val initialized
        get() = cards.size >= 2

    val size: Int
        get() = cards.size

    infix operator fun plus(card: Card) = Hands(cards + card)

    fun isBust(): Boolean = cards.sumOf { it.number.value } > BLACKJACK_SCORE

    companion object {
        private const val BLACKJACK_SCORE = 21
    }
}
