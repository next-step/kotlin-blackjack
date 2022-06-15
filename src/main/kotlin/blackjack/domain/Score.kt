package blackjack.domain

@JvmInline
value class Score(val value: Int) {
    init {
        require(value >= 0) { "점수는 0보다 커야 합니다" }
    }

    fun isBust() = this > BLACK_JACK_SCORE

    fun isBlackJack() = this == BLACK_JACK_SCORE

    operator fun plus(other: Score): Score {
        return Score(value + other.value)
    }

    operator fun compareTo(other: Score): Int {
        return value.compareTo(other.value)
    }

    companion object {
        private fun List<Score>.sum() = fold(Score(0)) { acc, score -> acc + score }

        fun sumBy(scores: List<Score>, hasSoft: Boolean): Score {
            var sum = scores.sum()
            if (hasSoft && sum <= SOFT_INCREASE_THRESHOLD) {
                sum += SOFT_INCREASE_SCORE
            }
            return sum
        }

        private val SOFT_INCREASE_SCORE = Score(10)
        private val SOFT_INCREASE_THRESHOLD = Score(11)

        private val BLACK_JACK_SCORE = Score(21)
    }
}
