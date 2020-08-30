package blackjack.model.card

import blackjack.model.BlackJackGame.Companion.BLACKJACK_SCORE
import blackjack.model.BlackJackGame.Companion.DEFAULT_CARD_COUNT

data class Cards(private val cards: Set<Card>) {

    constructor(vararg cards: Card) : this(cards.toSet())

    operator fun plus(newCard: Card): Cards = Cards(cards + newCard)

    fun score(): Int {
        val sum = cards.map(Card::score).sumBy { it }
        return CardScore.sumWithAce(sum, hasAce())
    }

    fun isSizeOfTwo(): Boolean = cards.size == DEFAULT_CARD_COUNT

    fun isBlackJack(): Boolean = score() == BLACKJACK_SCORE && isSizeOfTwo()

    fun isBust(): Boolean = score() > BLACKJACK_SCORE

    private fun hasAce() = cards.any { it.isAce() }

    override fun toString(): String = cards.joinToString { it.toString() }
}
