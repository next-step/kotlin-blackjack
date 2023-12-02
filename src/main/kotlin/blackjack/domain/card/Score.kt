package blackjack.domain.card

@JvmInline
value class Score(val value: Int) {
    constructor(cards: Cards) : this(cards.sumOf { card -> card.character.score })

    fun addBonusIfNotBust(): Score {
        if (canReceiveMoreCard()) {
            return Score(value + SOFT_SCORE)
        }
        return this
    }

    fun isBlackjackScore(): Boolean = value == BLACKJACK_SCORE

    fun overBlackjackScore(): Boolean = value > BLACKJACK_SCORE

    private fun canReceiveMoreCard(): Boolean = value + SOFT_SCORE <= BLACKJACK_SCORE

    companion object {
        private const val SOFT_SCORE = 10
        private const val BLACKJACK_SCORE = 21
    }
}
