package blackjack.domain.card

class Hands(private val cards: List<Card>) {
    constructor(vararg cards: Card): this(cards.toList())

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
