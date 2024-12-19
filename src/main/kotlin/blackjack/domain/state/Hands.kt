package blackjack.domain.state

import blackjack.domain.card.PlayingCard

class Hands(private val cards: List<PlayingCard> = emptyList()) : List<PlayingCard> by cards {
    constructor(vararg cards: PlayingCard) : this(cards.toList())
    fun score(): Int {

        val sum = cards.sumOf { it.score }
        val weight = getWeight()
        val total = sum + weight
        return if (total > BLACKJACK) sum else total
    }

    operator fun plus(card: PlayingCard): Hands {
        return Hands(cards + card)
    }

    fun isBlackJackScore(): Boolean = score() == BLACKJACK

    private fun getWeight() : Int   = if (isSoft()) ACE_WEIGHT else 0

    private fun isSoft(): Boolean {
        return cards.any { it.isAce }
    }

    companion object {
        private const val ACE_WEIGHT: Int = 10
        private const val BLACKJACK: Int = 21
    }
}
