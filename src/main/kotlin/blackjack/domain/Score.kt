package blackjack.domain

@JvmInline
value class Score(val value: Int) {

    init {
        require(value >= MIN_SCORE) { "점수는 $MIN_SCORE 이상이어야 합니다.." }
    }

    fun canHit(): Boolean {
        return !isBlackJack() && !isBust()
    }

    fun isBlackJack(): Boolean {
        return value == BLACK_JACK
    }

    fun isBust(): Boolean {
        return value > BLACK_JACK
    }

    val aceRevised: Score
        get() {
            val revised = value + ACE_REVISE
            if (revised <= BLACK_JACK) {
                return Score(revised)
            }
            return this
        }

    operator fun plus(other: Score): Score {
        return Score(value + other.value)
    }

    companion object {
        private const val BLACK_JACK = 21
        private const val ACE_REVISE = 10
        private const val MIN_SCORE = 0

        val INITIAL = Score(MIN_SCORE)
    }
}

fun List<Score>.sum(): Score {
    return fold(Score.INITIAL) { acc, score -> acc + score }
}
