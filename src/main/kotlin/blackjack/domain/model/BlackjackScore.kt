package blackjack.domain.model

@JvmInline
value class BlackjackScore private constructor(val value: Int) {

    init {
        require(value in MIN_VALUE..MAX_VALUE) {
            "블랙잭 스코어는 $MIN_VALUE~$MAX_VALUE 사이의 값이여야 합니다."
        }
    }

    fun isBlackjackScore(): Boolean = value == BUST_SCORE
    fun isBust(): Boolean = value > BUST_SCORE

    infix fun isHigherThan(number: Int): Boolean = value > number

    companion object {
        private const val MIN_VALUE = 1
        private const val MAX_VALUE = 31
        private const val BONUS_SCORE = 10
        private const val BUST_SCORE = 21

        fun create(cards: Cards): BlackjackScore {
            var totalNumber = cards.values.sumOf { card -> card.sign.number }
            if (cards.isContainAce() && (totalNumber + BONUS_SCORE) <= BUST_SCORE) {
                totalNumber += BONUS_SCORE
            }
            return BlackjackScore(totalNumber)
        }
    }
}
