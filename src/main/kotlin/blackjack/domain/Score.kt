package blackjack.domain


@JvmInline
value class Score private constructor(val value: Int) {

    operator fun plus(score: Score) = from(value + score.value)

    companion object {
        private const val MIN_SCORE = 0
        private const val MAX_SCORE = 100
        private val preparedScores = (MIN_SCORE..MAX_SCORE).associateWith(::Score)

        fun from(value: Int): Score = preparedScores[value] ?: Score(value)
    }
}
