package blackjack.domain.card

class Hands(val cards: Cards) {
    constructor(vararg cards: Card): this(Cards(cards.toList()))

    operator fun plus(otherCards: Cards): Hands = Hands(this.cards + otherCards)
    operator fun plus(otherCard: Card): Hands = Hands(this.cards + otherCard)

    fun hasTwo(): Boolean = cards.size == INITIAL_CARDS_SIZE

    fun score(): Score {
        val score = Score(cards.sumOf { it.character.score })
        if (isSoft()) {
            return score.addBonusIfNotBust()
        }
        return score
    }

    private fun isSoft(): Boolean = cards.any { it.isAce() }

    companion object {
        private const val INITIAL_CARDS_SIZE = 2
    }
}
