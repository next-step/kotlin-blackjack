package blackjack.domain.score

import blackjack.domain.card.CardNumber

@JvmInline
value class Score(private val value: Int) {
    init {
        require(value >= 0) { SCORE_VALUE_EXCEPTION }
    }

    fun isBust() = value > MAX_SCORE

    fun isBlackJack() = value == MAX_SCORE

    operator fun plus(other: Score) = Score(value + other.value)

    operator fun compareTo(score: Score): Int {
        return value.compareTo(score.value)
    }

    companion object {
        private const val SCORE_VALUE_EXCEPTION = "점수는 음수가 될 수 없습니다."
        const val MAX_SCORE = 21

        fun calculate(
            numbers: List<CardNumber>,
            isContainAce: Boolean,
        ): Score {
            val score = Score(numbers.sumOf { it.value })

            if (isContainAce && CardNumber.Ace.toEleven() + score.value <= MAX_SCORE) {
                return score + Score(CardNumber.Ace.toEleven())
            }

            return score
        }
    }
}
